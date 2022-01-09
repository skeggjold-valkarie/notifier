package ru.test.notifier.ui.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import java.io.Serializable
import android.R.drawable
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.content.res.AppCompatResources
import ru.test.notifier.R


typealias DialogListener = (String, Bundle) -> Unit

data class AnimationVector<T>(val startX: T, val startY: T, val endX: T, val endY: T):Serializable {
    override fun toString(): String = "[($startX, $startY) -> ($endX, $endY)]"
}

fun Resources.toBitmap(@DrawableRes id: Int): Bitmap = BitmapFactory.decodeResource(this, id)


fun Context.toBitmap(@DrawableRes id: Int): Bitmap? {
    val drawable = AppCompatResources.getDrawable(this, id)
    val width = drawable?.intrinsicWidth ?: 0
    val height = drawable?.intrinsicHeight ?: 0
    return when {
        drawable is BitmapDrawable -> drawable.bitmap
        width > 0 && height > 0 -> {
            Bitmap.createBitmap(width, height, ARGB_8888).also {
                val canvas = Canvas(it)
                drawable?.setBounds(0, 0, canvas.width, canvas.height)
                drawable?.draw(canvas)
            }
        }
        else -> null
    }
}