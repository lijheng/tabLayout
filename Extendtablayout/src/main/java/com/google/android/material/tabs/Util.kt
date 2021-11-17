package com.google.android.material.tabs

import android.content.Context
import android.graphics.RectF
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.Dimension
import com.summer.extendtablayout.DEFAULT_VALUE
import com.summer.extendtablayout.ExtendTabLayout
import com.summer.extendtablayout.util.px
import java.lang.reflect.Field
import kotlin.math.roundToInt

object Util {

    @Dimension(unit = Dimension.DP)
    private val MIN_INDICATOR_WIDTH = 10

    fun calculateIndicatorWidthForTab(
        tab: View?,
        marginStart: Int,
        marginEnd: Int,
        overStart: Int,
        overEnd: Int,
        width: Int
    ): RectF {
        if (tab == null) {
            return RectF()
        }
        return if (tab is TabLayout.TabView) {
            val rect = calculateTabViewContentBounds(
                tab, if (width != DEFAULT_VALUE) {
                    width
                } else {
                    MIN_INDICATOR_WIDTH
                }
            )
            if (marginStart != DEFAULT_VALUE
                || marginEnd != DEFAULT_VALUE
            ) {
                RectF(rect.left + marginStart, rect.top, rect.right - marginEnd, rect.bottom)
            } else if (overStart != DEFAULT_VALUE || overEnd != DEFAULT_VALUE) {
                RectF(rect.left - overStart, rect.top, rect.right + overEnd, rect.bottom)
            } else {
                val offset = (rect.width() - width) / 2f
                RectF(rect.left + offset, rect.top, rect.right - offset, rect.bottom)
            }
        } else {
            RectF(
                tab.left.toFloat(), tab.top.toFloat(), tab.right.toFloat(),
                tab.bottom.toFloat()
            )
        }

    }


    private fun calculateTabViewContentBounds(
        tabView: TabLayout.TabView, @Dimension(unit = Dimension.DP) minWidth: Int
    ): RectF {
        var tabViewContentWidth = tabView.contentWidth
        val tabViewContentHeight = tabView.contentHeight
        val minWidthPx = minWidth.px
        if (tabViewContentWidth < minWidthPx) {
            tabViewContentWidth = minWidthPx
        }
        val tabViewCenterX = (tabView.left + tabView.right) / 2
        val tabViewCenterY = (tabView.top + tabView.bottom) / 2
        val contentLeftBounds = tabViewCenterX - tabViewContentWidth / 2
        val contentTopBounds = tabViewCenterY - tabViewContentHeight / 2
        val contentRightBounds = tabViewCenterX + tabViewContentWidth / 2
        val contentBottomBounds = tabViewCenterY + tabViewCenterX / 2
        return RectF(
            contentLeftBounds.toFloat(),
            contentTopBounds.toFloat(), contentRightBounds.toFloat(), contentBottomBounds.toFloat()
        )
    }

    fun lerp(startValue: Int, endValue: Int, fraction: Float): Int {
        return startValue + (fraction * (endValue - startValue)).roundToInt()
    }

    fun createCustomView(context: Context, tabLayout: TabLayout): View {
        val customView = FrameLayout(context)
        val customTextView = TextView(context)
        customTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabLayout.tabTextSize)
        if (tabLayout.tabTextColors != null) {
            customTextView.setTextColor(tabLayout.tabTextColors)
        }
        customTextView.id = android.R.id.text1
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        customView.addView(customTextView, params)
        return customView
    }

    fun getTabTextSize(tabLayout: TabLayout): Float {
        return tabLayout.tabTextSize
    }
}