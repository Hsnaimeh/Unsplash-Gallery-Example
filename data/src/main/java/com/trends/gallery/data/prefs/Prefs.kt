package com.trends.gallery.data.prefs


/**
 * Created by Hisham Sanimeh.
 */
interface Prefs {

    var accessToken: String?

    var language: Language


    val isLoggedIn: Boolean
        get() = accessToken != null
}
