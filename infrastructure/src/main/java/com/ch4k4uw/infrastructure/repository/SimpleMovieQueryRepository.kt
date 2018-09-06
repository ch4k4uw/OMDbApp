package com.ch4k4uw.infrastructure.repository

import com.ch4k4uw.domain.abstraction.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.domain.abstraction.repository.specification.RepositorySpecification
import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.common.abstraction.factory.entity.CommonEntityFactory
import com.ch4k4uw.domain.common.abstraction.factory.value.CommonValueFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryDetailSpecification
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import com.ch4k4uw.infrastructure.abstraction.rest.Movie
import com.ch4k4uw.infrastructure.abstraction.rest.MovieType
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import io.reactivex.Observable
import java.security.InvalidParameterException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SimpleMovieQueryRepository @Inject constructor(
        private val schedulerProvider: SchedulerProvider,
        private val restController: RestController,
        private val entityFactory: CommonEntityFactory,
        private val valueFactory: CommonValueFactory) : MovieQueryRepository {

    private val dateFormat: DateFormat = SimpleDateFormat("DD MMM yyyy", Locale.US)

    override fun getById(spec: ByIdRepositorySpecification<MovieEntity, Long>, success: (MovieEntity) -> Unit, error: (Throwable) -> Unit) {
        if(spec is MovieQueryRepositoryDetailSpecification) {
            restController.getById(spec.id2, { rawDetail ->
                val actors = arrayListOf<MovieActorValue>()
                val ratings = arrayListOf<MovieRatingValue>()

                val rawActors = rawDetail.Actors?.split(",") ?: listOf()
                rawActors.forEach { rawActor ->
                    actors.add(valueFactory.newMovieActorValue(rawActor))
                }

                rawDetail.Ratings?.forEach { rating ->
                    ratings.add(valueFactory.newMovieRatingValue(rating.Source ?: "", rating.Value ?: ""))
                }

                val returnResultStep: (MovieTypeEntity) -> Unit = { movieTypeEntity ->
                    val rawDate =
                            if (!rawDetail.Released.isNullOrBlank())
                                try {
                                    dateFormat.parse(rawDetail.Released)
                                } catch (_: Exception) {
                                    Date(0)
                                }
                            else
                                Date(0)

                    val date = Calendar.getInstance()
                    date.time = rawDate

                    val result = entityFactory.newMovieEntity(
                            rawDetail.imdbID?.replace("tt", "")?.toLong() ?:0L,
                            rawDetail.imdbID ?: "",
                            rawDetail.Title ?: "",
                            rawDetail.Year ?: "",
                            rawDetail.Rated ?: "",
                            date,
                            rawDetail.Runtime ?: "",
                            rawDetail.Genre ?: "",
                            rawDetail.Director ?: "",
                            rawDetail.Writer ?: "",
                            actors,
                            rawDetail.Plot ?: "",
                            rawDetail.Language ?: "",
                            rawDetail.Country ?: "",
                            rawDetail.Awards ?: "",
                            rawDetail.Poster ?: "",
                            ratings,
                            rawDetail.Metascore ?: "",
                            if (!rawDetail.imdbRating.isNullOrBlank()) rawDetail.imdbRating?.toFloat() ?: 0f else 0f,
                            if (!rawDetail.imdbVotes.isNullOrBlank()) rawDetail.imdbVotes?.replace(",", "")?.toInt() ?: 0 else 0,
                            movieTypeEntity,
                            if (!rawDetail.totalSeasons.isNullOrBlank()) rawDetail.totalSeasons?.toInt() ?: 0 else 0
                    )

                    success(result)

                }

                if(!rawDetail.Type.isNullOrBlank()) {
                    restController.getMovieType(rawDetail.Type ?: "", { rawMovieType ->
                        returnResultStep(entityFactory.newMovieTypeEntity(rawMovieType.id.toLong(), rawMovieType.name))
                    }, error)
                } else {
                    returnResultStep(entityFactory.emptyMovieTypeEntity)
                }

            }, error)
        } else {
            throw InvalidParameterException()
        }
    }

    override fun find(spec: RepositorySpecification<MovieEntity, Long>, success: (List<MovieEntity>) -> Unit, error: (Throwable) -> Unit) {
        if(spec is MovieQueryRepositoryCatalogSpecification) {
            val searchMoviesSuccess: (List<Movie>) -> Unit = { rawMovies ->
                restController.getMovieTypes({ movieTypes ->
                    Observable
                            .defer {
                                val result = arrayListOf<MovieEntity>()
                                rawMovies.forEach { rawMovie ->
                                    val type = movieTypes.find { it.name.toLowerCase() == rawMovie.Type?.toLowerCase() ?: "" } ?: MovieType(0, "")
                                    result.add(
                                            entityFactory.newMovieEntity(
                                                    rawMovie.imdbID?.replace("tt", "")?.toLong() ?: 0L,
                                                    rawMovie.imdbID ?: "",
                                                    rawMovie.Title ?: "",
                                                    rawMovie.Year ?: "",
                                                    entityFactory.newMovieTypeEntity(type.id.toLong(), type.name),
                                                    rawMovie.Poster ?: ""
                                            )
                                    )
                                }
                                Observable.just(result)
                            }.compose(schedulerProvider.applySchedulers())
                            .subscribe(success, error)

                }, error)
            }

            val year = if (spec.year != null) spec.year.toString() else null
            val doSearch: (String?) -> Unit = { type ->
                restController.searchMovies(spec.title, type, year, spec.page, searchMoviesSuccess, error)
            }

            if(spec.typeId == null) {
                doSearch(null)
            } else {
                restController.getMovieType(spec.typeId!!.toInt(), { doSearch(it.name) }, error)
            }

        } else {
            throw InvalidParameterException()
        }
    }
}