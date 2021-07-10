package com.trends.gallery.core

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController

/**
 * Created by Hisham Sanimeh.
 */
fun View.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (mayNavigate()) findNavController().navigate(
        resId, args,
        navOptions, navigatorExtras
    )
}

fun View.navigateSafe(
    deepLink: Uri,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (mayNavigate()) findNavController().navigate(deepLink, navOptions, navigatorExtras)
}

fun View.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    if (mayNavigate()) findNavController().navigate(directions, navOptions)
}

fun View.navigateSafe(
    directions: NavDirections,
    navigatorExtras: Navigator.Extras
) {
    if (mayNavigate()) findNavController().navigate(directions, navigatorExtras)
}

fun View.mayNavigate(): Boolean {
    val destinationIdInNavController = findNavController().currentDestination?.id

    val destinationIdOfThisFragment = getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

    return if (destinationIdInNavController == destinationIdOfThisFragment) {
        setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
        true
    } else {
        false
    }
}
