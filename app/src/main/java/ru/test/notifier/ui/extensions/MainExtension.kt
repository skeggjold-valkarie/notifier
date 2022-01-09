package ru.test.notifier.ui.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.test.notifier.ui.model.ContextButton
import ru.test.notifier.ui.model.ContextButtonModel
import java.io.Serializable


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

fun List<ContextButtonModel>.mapToButtons(viewHolder: ViewHolder): List<ContextButton> {
    val view = viewHolder.itemView
    var left = view.left
    return map {
        ContextButton(
            code = it.code,
            position = viewHolder.adapterPosition,
            bounds = Rect(left, view.top, left + it.size, view.bottom)
        ).also { button -> left += button.bounds.right }
    }
}

fun Rect.hitTest(x: Int, y: Int) = x in left..right && y in top..bottom