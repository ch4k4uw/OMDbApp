package com.ch4k4uw.omdbapp.shadows

import android.content.Context
import android.support.design.widget.CollapsingToolbarLayout
import android.util.AttributeSet
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowFrameLayout

@Implements(CollapsingToolbarLayout::class)
class ShadowCollapsingToolbarLayout: ShadowFrameLayout() {
    override fun __constructor__(context: Context?, attributeSet: AttributeSet?, defStyle: Int) {
        super.__constructor__(context, attributeSet, defStyle)
    }

    @Implementation
    fun onAttachedToWindow() {

    }
}