package com.trends.gallery.core

/**
 * Created by Hisham Sanimeh.
 */
fun <T> List<T>.clone(): List<T> {
    return mutableListOf<T>().apply {
        this@clone.forEach { add(it) }
    }
}
