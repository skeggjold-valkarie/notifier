package ru.test.notifier.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.test.notifier.NotifierApplication
import ru.test.notifier.ui.extensions.hitTest
import ru.test.notifier.ui.extensions.mapToButtons
import ru.test.notifier.ui.model.ContextButton
import ru.test.notifier.ui.model.ContextButtonModel

@SuppressLint("ClickableViewAccessibility")
class SwipeHelper(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val buttonModels: List<ContextButtonModel>,
    private val listener: ItemTouchListener
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT
) {

    private val gestureListener = object : SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val button = buttons[selected]?.find { it.bounds.hitTest(e.x.toInt(), e.y.toInt()) }
            button?.let { listener.onAction(it.position, it.code) }
            return button != null
        }
    }
    private val gestureDetector = GestureDetector(context, gestureListener)
    private val onTouchListener = OnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    private var buttons = mutableMapOf<Int, List<ContextButton>>()

    private var bitmapCache = NotifierApplication.getInstance().getBitmapCache()

    private var selected = NO_ONE_SELECTED

    private val paint = Paint()
    private var multiplayer = DEFAULT_MULTIPLIER

    private val swipedSet = mutableSetOf<Int>()

    init {
        recyclerView.setOnTouchListener(onTouchListener)
    }

    override fun onMove(view: RecyclerView, holder: ViewHolder, target: ViewHolder): Boolean = false

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        Log.e("testLog", "onSwiped")
        swipedSet.add(viewHolder.adapterPosition)
    }

    override fun getSwipeThreshold(viewHolder: ViewHolder): Float {
        return getThreshold(viewHolder.itemView)
    }

    override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.let{
            val list = buttonModels.mapToButtons(it)
            buttons[it.adapterPosition] ?: buttons.set(it.adapterPosition, list)
        }
        if (actionState == ACTION_STATE_SWIPE){
            swipedSet.forEach { index ->
                recyclerView.adapter?.notifyItemChanged(index)
            }
            swipedSet.clear()
        }
    }

    override fun onChildDraw(canvas: Canvas, rv: RecyclerView, viewHolder: ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isActive: Boolean) {
        Log.e("testLog", "onChildDraw")
        val itemView = viewHolder.itemView
        selected = viewHolder.adapterPosition

        val translationX = dX.takeIf { actionState != ACTION_STATE_SWIPE } ?: kotlin.run {
            dX / getThreshold(itemView)
        }.also { drawButtons(canvas, itemView, selected) }

       super.onChildDraw(canvas, recyclerView, viewHolder, translationX, dY, actionState, isActive)
    }

    private fun getThreshold(view: View): Float =
        multiplayer.takeIf { it != DEFAULT_MULTIPLIER } ?: kotlin.run {
            view.width / buttonModels.sumOf { it.size }.toFloat()
        }.also { multiplayer = it }

    private fun drawButtons(canvas: Canvas, view: View, position: Int){
        paint.color = Color.parseColor("#EECC55")
        val space = Rect(view.left, view.top, view.width, view.bottom)
        canvas.drawRect(space, paint)

        buttons[position]?.forEach { drawButton(canvas, it) }
    }

    private fun drawButton(canvas: Canvas, button: ContextButton){
        var textY = button.bounds.height()/2 - DEFAULT_TEXT_SIZE
        bitmapCache[button.res]?.let { bitmap ->
            canvas.drawBitmap(
                bitmap,
                button.bounds.left + (button.bounds.width() - bitmap.width.toFloat()) / 2,
                button.bounds.top + (button.bounds.height() - bitmap.height - DEFAULT_TEXT_SIZE) / 2,
                paint
            )
            textY += bitmap.height
        }

        val label = Rect()
        paint.textSize = DEFAULT_TEXT_SIZE
        paint.isAntiAlias = true
        paint.getTextBounds(button.label, 0, button.label.length, label)
        paint.color = Color.WHITE

        canvas.drawText(
            button.label,
            button.bounds.left + (button.bounds.width() - label.width().toFloat()) / 2,
            button.bounds.top + textY,
            paint
        )
    }

    companion object {
        const val DEFAULT_MULTIPLIER = 0.5F
        const val FLING_MULTIPLIER = 1F
        const val DEFAULT_TEXT_SIZE = 30F

        const val NO_ONE_SELECTED = -1
    }

}
