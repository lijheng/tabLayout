package com.google.android.material.tabs

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.FloatRange
import kotlin.math.cos
import kotlin.math.sin

internal open class ExpandElasticTabIndicatorInterpolator(
    marginStart: Int, marginEnd: Int,
    overStart: Int, overEnd: Int, width: Int
) : ExpandTabIndicatorInterpolator(marginStart, marginEnd, overStart, overEnd, width) {


    override fun setIndicatorBoundsForOffset(
        tabLayout: TabLayout,
        startTitle: View?,
        endTitle: View?,
        offset: Float,
        indicator: Drawable
    ) {
        val startIndicator =
            Util.calculateIndicatorWidthForTab(
                startTitle, marginStart, marginEnd, overStart, overEnd, width
            )
        val endIndicator =
            Util.calculateIndicatorWidthForTab(
                endTitle, marginStart, marginEnd, overStart, overEnd, width
            )

        val leftFraction: Float
        val rightFraction: Float

        val isMovingRight = startIndicator.left < endIndicator.left

        if (isMovingRight) {
            leftFraction = accInterp(offset)
            rightFraction = decInterp(offset)
        } else {
            leftFraction = decInterp(offset)
            rightFraction = accInterp(offset)
        }
        indicator.setBounds(
            Util.lerp(
                startIndicator.left.toInt(), endIndicator.left.toInt(), leftFraction
            ),
            indicator.bounds.top,
            Util.lerp(
                startIndicator.right.toInt(), endIndicator.right.toInt(), rightFraction
            ),
            indicator.bounds.bottom
        )
    }

    private fun decInterp(@FloatRange(from = 0.0, to = 1.0) fraction: Float): Float {
        return sin(fraction * Math.PI / 2.0).toFloat()
    }

    private fun accInterp(@FloatRange(from = 0.0, to = 1.0) fraction: Float): Float {
        return (1.0 - cos(fraction * Math.PI / 2.0)).toFloat()
    }
}