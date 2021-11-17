package com.summer.extendtablayout.util

import android.util.Log
import com.google.android.material.tabs.ExpandElasticTabIndicatorInterpolator
import com.google.android.material.tabs.ExpandTabIndicatorInterpolator
import com.google.android.material.tabs.TabLayout
import com.summer.extendtablayout.DEFAULT_VALUE
import java.lang.IllegalArgumentException
import java.lang.reflect.Field

object TabIndicatorInterpolatorFactory {

    private const val TAG = "IndicatorInterpolator"

    /**
     * 创建指示器
     */
    fun createTabIndicatorInterpolator(
        tabLayout: TabLayout,
        marginStart: Int = DEFAULT_VALUE,
        marginEnd: Int = DEFAULT_VALUE,
        overStart: Int = DEFAULT_VALUE,
        overEnd: Int = DEFAULT_VALUE,
        width: Int = DEFAULT_VALUE,
    ): Boolean {
        try {
            val field: Field = TabLayout::class.java.getDeclaredField("tabIndicatorInterpolator")
            val tabIndicatorAnimationMode: Int = tabLayout.tabIndicatorAnimationMode
            field.isAccessible = true
            field[tabLayout] = when (tabIndicatorAnimationMode) {
                TabLayout.INDICATOR_ANIMATION_MODE_LINEAR -> {
                    ExpandTabIndicatorInterpolator(
                        marginStart, marginEnd, overStart, overEnd, width
                    )
                }
                TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC -> {
                    ExpandElasticTabIndicatorInterpolator(
                        marginStart, marginEnd, overStart, overEnd, width
                    )
                }
                else -> {
                    throw IllegalArgumentException("$tabIndicatorAnimationMode is not a valid TabIndicatorAnimationMode")
                }
            }
            return true
        } catch (e: NoSuchFieldException) {
            Log.e(TAG, "setTabIndicatorAnimationMode: NoSuchFieldException")
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "setTabIndicatorAnimationMode: IllegalAccessException")
        }
        return false
    }

}