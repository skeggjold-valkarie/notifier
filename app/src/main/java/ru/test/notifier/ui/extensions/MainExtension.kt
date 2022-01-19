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
import ru.test.notifier.domain.model.EventModel
import ru.test.notifier.ui.model.ContextButton
import ru.test.notifier.ui.model.ContextButtonModel
import java.io.Serializable


typealias DialogListener = (String, Bundle) -> Unit

data class Box<T>(val left: T, val top: T, val right: T, val bottom: T):Serializable {
    override fun toString(): String = "[($left, $top) - ($right, $bottom)]"
}

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
    var right = view.right
    return asReversed().map {
        ContextButton(
            code = it.code,
            label = it.label,
            position = viewHolder.adapterPosition,
            res = it.res,
            bounds = Rect(right - it.size, view.top, right, view.bottom)
        ).also { button -> right -= button.bounds.width() }
    }
}

fun Rect.hitTest(x: Int, y: Int) = x in left..right && y in top..bottom