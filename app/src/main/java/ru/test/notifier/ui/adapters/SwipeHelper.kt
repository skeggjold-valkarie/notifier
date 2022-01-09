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
import ru.test.notifier.ui.extensions.hitTest
import ru.test.notifier.ui.extensions.mapToButtons
import ru.test.notifier.ui.model.ContextButton
import ru.test.notifier.ui.model.ContextButtonModel
import java.util.*

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

    private var swipePosition = -1
    private val gestureListener = object : SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val button = buttons[selected]?.find { it.bounds.hitTest(e.x.toInt(), e.y.toInt()) }
            button?.let { listener.onAction(it.position, it.code) }
            return button != null
        }
    }
    private val gestureDetector = GestureDetector(context, gestureListener)
    private val onTouchListener = OnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    private val removeQueue: Queue<Int>? = null
    private var buttons = mutableMapOf<Int, List<ContextButton>>()

    private var selected = -1

    private val paint = Paint()
    private var multiplayer = DEFAULT_MULTIPLIER

    init {
        recyclerView.setOnTouchListener(onTouchListener)
        Log.e("testLog", "new ${recyclerView.measuredWidth}")
    }

    override fun onMove(view: RecyclerView, holder: ViewHolder, target: ViewHolder): Boolean {
        Log.e("testLog", "onMove ")
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        Log.e("testLog", "direction $direction ")
        selected = -1
    }

    override fun getSwipeThreshold(viewHolder: ViewHolder): Float {
        return getThreshold(viewHolder.itemView)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder) {
        Log.e("testLog", "clearView");
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        Log.e("testLog", "onSelectedChanged $actionState");
        viewHolder?.let{
            val list = buttonModels.mapToButtons(it)
            buttons[it.adapterPosition] ?: buttons.set(it.adapterPosition, list)
        }
    }

    override fun onChildDraw(c: Canvas, rv: RecyclerView, viewHolder: ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isActive: Boolean) {

        selected = viewHolder.adapterPosition

        val itemView = viewHolder.itemView
        val translationX = dX.takeIf { actionState != ACTION_STATE_SWIPE } ?: kotlin.run {
            dX / getThreshold(itemView)
        }.also { drawButtons(c, itemView, selected, it) }

        super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isActive)
    }

    private fun getThreshold(view: View): Float =
        multiplayer.takeIf { it != DEFAULT_MULTIPLIER } ?: kotlin.run {
            view.width / buttonModels.sumOf { it.size }.toFloat()
        }.also { multiplayer = it }

    private fun drawButtons(c: Canvas, view: View, position: Int, translationX: Float){
        paint.color = Color.BLACK
        c.drawRect(Rect(view.left, view.top, view.width, view.bottom), paint)
//        float right = itemView.getRight();
//        float dButtonWidth = 200;
////        if (zzz){
//            zzz = false;
//            p.setColor(Color.parseColor("#FF0055"));
//
//            for (MyButton button : buffer){
//                float left = right - dButtonWidth;
//                button.onDraw(c, new RectF(left, itemView.getTop(), right, itemView.getBottom()), pos);
//                right = left;
//            }
    }

    private fun drawButton(){
        //        fun onDraw(c: Canvas, rectF: RectF, pos: Int) {
//            val p = Paint()
//            //            p.setColor(color);
////            c.drawRect(rectF, p);
//            p.textSize = textSize.toFloat()
//            val r = Rect()
//            val cHeight = rectF.height()
//            val cWidth = rectF.width()
//            //            p.setFlags(Paint.ANTI_ALIAS_FLAG);
//            p.isAntiAlias = true
//            p.getTextBounds(text, 0, text.length, r)
//            val x = 0f
//            val y = 0f
//            val tx = (rectF.width() - r.width()) / 2
//            var ty: Float = (rectF.height() + textSize) / 2
//            Log.e(
//                "zzzzzz",
//                rectF.width()
//                    .toString() + " | " + rectF.height() + " | " + r.width() + " | " + r.height()
//            )
//            var bitmap: Bitmap? = null
//            if (imageResId != 0) {
//                val d = ContextCompat.getDrawable(context, imageResId)
//                bitmap = drawableToBitmap(d)
//                Log.e(
//                    "zzzzzz",
//                    bitmap!!.width.toString() + " | " + bitmap.height + " | " + rectF.width() + " | " + rectF.height()
//                )
//                c.drawBitmap(
//                    bitmap,
//                    rectF.left + (rectF.width() - bitmap.width) / 2,
//                    rectF.top + (rectF.height() - bitmap.height - textSize) / 2,
//                    p
//                )
//            }
//            if (bitmap != null) {
//                ty += textSize.toFloat()
//            }
//
////            p.setColor(Color.BLACK);
////            c.drawRect(new RectF(rectF.left + tx, rectF.top + ty - r.height(), rectF.left + tx + r.width(), rectF.top + ty), p);
//
//            //Text
//            p.color = Color.WHITE
//
//            //If just show Text
//            c.drawText(text, rectF.left + tx, rectF.top + ty, p)
//            Log.e("zzzzzz", rectF.left.toString() + " | " + rectF.top)
//            clickRegion = rectF
//            pos = pos
//        }
//    }
    }

    companion object {
        const val DEFAULT_MULTIPLIER = 0.5F
    }

}
