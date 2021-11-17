package com.summer.extendtablayout.util

import android.content.res.Resources
import android.util.TypedValue

private val resources = Resources.getSystem()
fun Int.dp(px: Int) {

}

val Int.px: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }