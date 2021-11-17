package com.summer.extendtablayout

import android.content.Context
import kotlin.jvm.JvmOverloads
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintLayout
import android.graphics.drawable.Drawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.tabs.Util
import com.summer.extendtablayout.util.TabIndicatorInterpolatorFactory
import org.w3c.dom.Text
import java.lang.reflect.Field

/**
 * 一个继承于[TabLayout]
 * 通过customView的方式改变tabLayout的indicator，让indicator的宽度等于文字的宽度
 * 且 可以通过属性设置indicator距离tabLayout的左侧右侧距离
 *
 * 2021年11月15日更新  为了切换时Indicator动画能够正常执行，修改原生的Indicator,而不采用自定义CustomView的方式
 */
const val DEFAULT_VALUE = 0
class ExtendTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = com.google.android.material.R.attr.tabStyle
) : TabLayout(context, attrs, defStyleAttr), OnTabSelectedListener {

    private val indicatorWidth: Int //指示器宽度

    private val tabSelectTextSize: Int
    private val indicatorMarginStart: Int
    private val indicatorMarginEnd: Int
    private val indicatorOverStart: Int
    private val indicatorOverEnd: Int

    private var tabIndicatorAnimationMode: Int? = null


    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ExtendTabLayout)
        indicatorMarginStart = array.getDimensionPixelSize(
            R.styleable.ExtendTabLayout_extendTabIndicatorMarginStart,
            DEFAULT_VALUE
        )
        indicatorMarginEnd = array.getDimensionPixelSize(
            R.styleable.ExtendTabLayout_extendTabIndicatorMarginEnd,
            DEFAULT_VALUE
        )
        indicatorWidth =
            array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorWidth, DEFAULT_VALUE)
        indicatorOverStart = array.getDimensionPixelSize(
            R.styleable.ExtendTabLayout_extendTabIndicatorOverStart,
            DEFAULT_VALUE
        )
        indicatorOverEnd = array.getDimensionPixelSize(
            R.styleable.ExtendTabLayout_extendTabIndicatorOverEnd,
            DEFAULT_VALUE
        )
        tabSelectTextSize =
            array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabSelectTextSize, DEFAULT_VALUE)
        array.recycle()
        tabIndicatorAnimationMode?.apply { setTabIndicatorAnimationMode(this) }
        if (tabSelectTextSize != DEFAULT_VALUE) {
            addOnTabSelectedListener(this)
        }
    }

    override fun addTab(tab: Tab, position: Int, setSelected: Boolean) {
        if (tabSelectTextSize != DEFAULT_VALUE) {
            tab.customView = Util.createCustomView(context, this)
        }
        super.addTab(tab, position, setSelected)
    }



    override fun setTabIndicatorAnimationMode(tabIndicatorAnimationMode: Int) {
        this.tabIndicatorAnimationMode = tabIndicatorAnimationMode
        if (indicatorMarginStart != DEFAULT_VALUE || indicatorMarginEnd != DEFAULT_VALUE || indicatorOverStart != DEFAULT_VALUE || indicatorOverEnd != DEFAULT_VALUE) {
            if (!TabIndicatorInterpolatorFactory.createTabIndicatorInterpolator(
                    this,
                    indicatorMarginStart,
                    indicatorMarginEnd,
                    indicatorOverStart,
                    indicatorOverEnd,
                    indicatorWidth
                )
            ) {
                super.setTabIndicatorAnimationMode(tabIndicatorAnimationMode)
            }
            return
        }
        super.setTabIndicatorAnimationMode(tabIndicatorAnimationMode)
    }

    override fun onTabSelected(tab: Tab) {
        if (tabSelectTextSize == 0) return
        changeTextSize(tab, tabSelectTextSize.toFloat())

    }

    override fun onTabUnselected(tab: Tab) {
        changeTextSize(tab, Util.getTabTextSize(this))
    }
    override fun onTabReselected(tab: Tab) {
        if (tabSelectTextSize == 0) return
        changeTextSize(tab, tabSelectTextSize.toFloat())
    }

    /**
     * 通过反射改变文字大小
     */
    private fun changeTextSize(tab: Tab, tabSelectTextSize: Float) {
        tab.customView?.apply {
            val textView =findViewById<TextView>(android.R.id.text1)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabSelectTextSize)
        }
    }


}