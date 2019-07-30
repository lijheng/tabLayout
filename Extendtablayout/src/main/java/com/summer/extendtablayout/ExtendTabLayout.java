package com.summer.extendtablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.tabs.TabLayout;

/**
 * 一个继承于{@link TabLayout}
 * 通过customView的方式改变tabLayout的indicator，让indicator的宽度等于文字的宽度
 * 且 可以通过属性设置indicator距离tabLayout的左侧右侧距离
 */
public class ExtendTabLayout extends TabLayout implements TabLayout.BaseOnTabSelectedListener {

    private final int indicatorHeight;
    @ColorInt
    @ColorRes
    private int indicatorSelectColor;

    private final int indicatorWidth;//指示器宽度

    private @DrawableRes
    int indicatorDrawable;

    private float tabTextSize, tabSelectTextSize, defaultTextSize;

    private final int indicatorMarginStart;

    private final int indicatorMarginEnd;

    private final int indicatorOverStart, indicatorOverEnd;

    public ExtendTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.tabStyle);
    }

    public ExtendTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ExtendTabLayout);
        indicatorHeight = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorHeight, 0);
        indicatorSelectColor = array.getColor(R.styleable.ExtendTabLayout_extendTabIndicatorSelectColor, 0);
        indicatorMarginStart = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorMarginStart, 0);
        indicatorMarginEnd = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorMarginEnd, 0);
        tabTextSize = (float) array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabTextSize, 0);
        indicatorDrawable = array.getResourceId(R.styleable.ExtendTabLayout_extendTabIndicatorDrawable, 0);
        indicatorWidth = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorWidth, 0);
        indicatorOverStart = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorOverStart, 0);
        indicatorOverEnd = array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabIndicatorOverEnd, 0);
        tabSelectTextSize = (float) array.getDimensionPixelSize(R.styleable.ExtendTabLayout_extendTabSelectTextSize, 0);
        array.recycle();

        addOnTabSelectedListener(this);
    }


    @Override
    public void addTab(@NonNull Tab tab, int position, boolean setSelected) {
        tab.setCustomView(createTabCustomView(tab.getText(), tab.view));
        super.addTab(tab, position, setSelected);
    }

    /**
     * 设置tab
     */
    private View createTabCustomView(CharSequence text, View tabView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_extentd_tab, (LinearLayout) tabView, false);
        TextView textView = view.findViewById(R.id.layout_extend_tab_tv_content);
        textView.setText(text);
        textView.setTextColor(getTabTextColors());
        if (tabTextSize != 0f) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
        } else {
            defaultTextSize = textView.getTextSize();
        }
        textView.setPadding(indicatorOverStart, 0, indicatorOverEnd, 0);
        View indicator = view.findViewById(R.id.layout_extend_tab_indicator);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) indicator.getLayoutParams();
        params.height = indicatorHeight;
        params.leftMargin = indicatorMarginStart;
        params.rightMargin = indicatorMarginEnd;
        params.width = indicatorWidth;
        indicator.setLayoutParams(params);

        indicator.setBackground(createStateListDrawable());

        return view;
    }

    /**
     * 创建StateListDrawable
     *
     * @return
     */
    private StateListDrawable createStateListDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = null;
        if (indicatorDrawable == 0) {
            drawable = new ColorDrawable(indicatorSelectColor);
        } else {
            drawable = getResources().getDrawable(indicatorDrawable);
        }
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, drawable);
        stateListDrawable.addState(new int[]{}, new ColorDrawable());
        return stateListDrawable;
    }

    @Override
    public void onTabSelected(Tab tab) {
        if (((int) tabSelectTextSize) == 0) return;
        changeTabTextSize(tab, tabSelectTextSize);
    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {
        if (((int) tabSelectTextSize) == 0) return;
        changeTabTextSize(tab, ((int) tabTextSize) == 0 ? defaultTextSize : tabTextSize);
    }

    /**
     * 改变tab的字体大小
     *
     * @param tab
     * @param textSize
     */
    private void changeTabTextSize(Tab tab, float textSize) {
        View view = tab.getCustomView();
        if (view == null) return;
        TextView tv = view.findViewById(R.id.layout_extend_tab_tv_content);
        if (tv != null) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
    }
}
