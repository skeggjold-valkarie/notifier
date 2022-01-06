package ru.test.notifier.view.extensions

import android.os.Bundle
import java.io.Serializable

typealias DialogListener = (String, Bundle) -> Unit

data class AnimationVector<T>(val startX: T, val startY: T, val endX: T, val endY: T):Serializable {
    override fun toString(): String = "[($startX, $startY) -> ($endX, $endY)]"
}