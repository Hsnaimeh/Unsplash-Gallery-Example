package com.trends.gallery.network.handler

import kotlin.reflect.KClass

/**
 * Created by Hisham Sanimeh.
 */
@Suppress("UNCHECKED_CAST")
class ErrorCatcher(private val throwable: Throwable) {

    private var handled: Boolean = false

    fun <T : Throwable> catch(type: KClass<T>, handler: (T) -> Unit): ErrorCatcher {
        if (handled) {
            return this
        }
        if (type == throwable::class) {
            handled = true
            handler(throwable as T)
        }
        return this
    }

    fun catch(handler: (Throwable) -> Unit) {
        if (handled) {
            return
        }
        handled = true
        handler(throwable)
    }
}
