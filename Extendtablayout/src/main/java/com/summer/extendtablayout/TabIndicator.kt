package com.summer.extendtablayout

import androidx.annotation.Px
import com.google.android.material.tabs.TabLayout
import com.summer.extendtablayout.util.TabIndicatorInterpolatorFactory

object TabIndicator {

    /**
     * 距离文本左右marginStart marginEnd 创建指示器
     */
    @JvmStatic
    fun buildIndicatorByMarginText(
        tabLayout: TabLayout, @Px marginStart: Int, @Px marginEnd: Int
    ): Boolean {
        return TabIndicatorInterpolatorFactory.createTabIndicatorInterpolator(
            tabLayout,
            marginStart,
            marginEnd
        )
    }

    /**
     * 超出文本宽度 overStart 和 overEnd 创建指示器
     */
    @JvmStatic
    fun buildIndicatorByOverText(
        tabLayout: TabLayout, @Px overStart: Int, @Px overEnd: Int
    ): Boolean {
        return TabIndicatorInterpolatorFactory.createTabIndicatorInterpolator(
            tabLayout,
            overStart = overStart, overEnd = overEnd
        )
    }

    /**
     * 创建指定宽度的指示器
     */
    @JvmStatic
    fun buildIndicatorByWidth(tabLayout: TabLayout, @Px indicatorWidth: Int): Boolean {
        return TabIndicatorInterpolatorFactory.createTabIndicatorInterpolator(
            tabLayout, width = indicatorWidth
        )
    }
}