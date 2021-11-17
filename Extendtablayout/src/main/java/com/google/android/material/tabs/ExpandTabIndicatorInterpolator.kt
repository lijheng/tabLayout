package com.google.android.material.tabs

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View

internal const val TAG = "ExpandTabIndicatorInter"

internal open class ExpandTabIndicatorInterpolator(
    protected val marginStart: Int,
    protected val marginEnd: Int,
    protected val overStart: Int,
    protected val overEnd: Int,
    protected val width:Int
) : TabIndicatorInterpolator() {

    override fun setIndicatorBoundsForOffset(
        tabLayout: TabLayout,
        startTitle: View?,
        endTitle: View?,
        offset: Float,
        indicator: Drawable
    ) {
        val startIndicator = Util.calculateIndicatorWidthForTab(
            startTitle, marginStart, marginEnd, overStart, overEnd,width
        )
        val endIndicator = Util.calculateIndicatorWidthForTab(
            endTitle, marginStart, marginEnd, overStart, overEnd,width
        )
        indicator.setBounds(
            Util.lerp(
                startIndicator.left.toInt(),
                endIndicator.left.toInt(), offset
            ),
            indicator.bounds.top,
            Util.lerp(
                startIndicator.right.toInt(),
                endIndicator.right.toInt(), offset
            ),
            indicator.bounds.bottom
        )
    }

    override fun setIndicatorBoundsForTab(tabLayout: TabLayout, tab: View?, indicator: Drawable) {
        val startIndicator = Util.calculateIndicatorWidthForTab(
            tab, marginStart, marginEnd, overStart, overEnd,width
        )
        indicator.setBounds(
            startIndicator.left.toInt(),
            indicator.bounds.top,
            startIndicator.right.toInt(),
            indicator.bounds.bottom
        )
    }
}
