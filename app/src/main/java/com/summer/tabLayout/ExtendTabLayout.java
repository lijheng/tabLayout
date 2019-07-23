package com.summer.tabLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.tabs.TabLayout;

/**
 * 一个可以{@link TabLayout}
 * 通过customView的方式改变tabLayout的indicator，让indicator的宽度等于文字的宽度
 * 且 可以通过属性设置indicator距离tabLayout的左侧右侧距离
 */
public class ExtendTabLayout extends TabLayout {

    private final int indicatorHeight;
    @ColorInt
    @ColorRes
    private int indicatorSelectColor;

    private final int indicatorMarginStart;

    private final int indicatorMarginEnd;

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
        array.recycle();
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
        View indicator = view.findViewById(R.id.layout_extend_tab_indicator);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) indicator.getLayoutParams();
        params.height = indicatorHeight;
        params.leftMargin = indicatorMarginStart;
        params.rightMargin = indicatorMarginEnd;
        indicator.setLayoutParams(params);
        indicator.setBackgroundColor(indicatorSelectColor);
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
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(indicatorSelectColor));
        stateListDrawable.addState(new int[]{}, new ColorDrawable());
        return stateListDrawable;
    }
}
