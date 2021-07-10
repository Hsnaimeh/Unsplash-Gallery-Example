package com.trends.gallery.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

/**
 * Created by Hisham Sanimeh.
 */
fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
fun View.isPermissionGranted(permission: String): Boolean {
    return context.isPermissionGranted(permission)
}
