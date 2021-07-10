package com.trends.gallery.core

import android.text.TextUtils
import android.view.View
import java.util.*

/**
 * Created by Hisham Sanimeh.
 */
fun isRtl(): Boolean {
    return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
}
