package com.trends.gallery.core

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment

/**
 * Created by Hisham Sanimeh.
 */
fun Fragment?.startAppSettingsIntent(requestCode: Int) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", this?.context?.packageName, null)
    intent.data = uri
    this?.context?.packageManager?.let { packageManager ->
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, requestCode)
        }
    }
}

fun Activity?.startAppSettingsIntent(requestCode: Int) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", this?.packageName, null)
    intent.data = uri
    this?.packageManager?.let { packageManager ->
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, requestCode)
        }
    }
}

fun Fragment.startDialIntent(phoneNumber: String?) {
    if (phoneNumber == null) {
        return
    }
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    if (intent.resolveActivity(requireContext().packageManager) != null) {
        startActivity(intent)
    }
}
