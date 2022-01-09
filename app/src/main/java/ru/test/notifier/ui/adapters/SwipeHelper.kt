package ru.test.notifier.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.test.notifier.ui.model.ContextButtonModel
import java.util.*

@SuppressLint("ClickableViewAccessibility")
class SwipeHelper(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val buttons: List<ContextButtonModel>,
    private val listener: ItemTouchListener
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT
) {

    private var swipePosition = -1
    private val gestureListener = GestureListener()
    private val gestureDetector = GestureDetector(context, gestureListener)
    private val onTouchListener = OnTouchListener { _, event -> gestureDetector.onTouchEvent(event) }
    private val removeQueue: Queue<Int>? = null

    private val paint = Paint()
    private var multiplayer = 0F

    init {
        recyclerView.setOnTouchListener(onTouchListener)
    }




    override fun onMove(view: RecyclerView, holder: ViewHolder, target: ViewHolder): Boolean {
        Log.e("testLog", "onMove ");
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        Log.e("testLog", "direction $direction ");
        val pos = viewHolder.adapterPosition
//        if (swipePosition != pos) {
//            removeQueue.add(swipePosition)
//        }
//        swipePosition = pos
//        if (buttonBuffer.containsKey(swipePosition)) {
//            buttonList = buttonBuffer.get(swipePosition)
//        } else {
//            buttonList.clear()
//        }
//        buttonBuffer.clear()
//        swipeThreshold = 0.5f * buttonList.size * buttonWidth
//        recoverSwipedItem()

//        recyclerView.adapter?.notifyItemChanged(position)
//
//        val holder = viewHolder as? EventsAdapter.ItemViewHolder
//        val view = holder?.itemView
//
//        when(direction){
//            ItemTouchHelper.LEFT -> {
//                if(holder?.foreground?.x != null && view?.width != null && holder.foreground.x < -view.width * 0.9 ){
//                    listener.swipeLeft(viewHolder.adapterPosition)
//                }
//            }
//
//        }
    }

    override fun getSwipeThreshold(viewHolder: ViewHolder): Float {
        return 0.8f
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder) {
        Log.e("testLog", "clearView");
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
//        super.onSelectedChanged(viewHolder, ItemTouchHelper.ACTION_STATE_IDLE)
        Log.e("testLog", "onSelectedChanged $actionState");
    }

    override fun onChildDraw(c: Canvas, rv: RecyclerView, viewHolder: ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isActive: Boolean) {

        multiplayer = buttons.sumOf { it.size }.toFloat() / viewHolder.itemView.width
        val position = viewHolder.adapterPosition
        var translationX = dX

        val itemView = viewHolder.itemView
        if (position < 0) {
            swipePosition = position
            return
        }

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            translationX = dX * multiplayer
//                drawButton(c, itemView, buffer, position, translationX)
        }
        super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isActive)

//        val holder = viewHolder as? EventsAdapter.ItemViewHolder
//        val view = holder?.itemView
//        holder?.foreground?.setY(holder!!.itemView.getTop().toFloat());
//        holder?.background?.visibility = if (isCurrentlyActive) View.VISIBLE else View.GONE
//        Log.e("testLog", "state $actionState | $isActive | $dX | $dY")
//        val translationX: Float = minOf(-dX, viewHolder.itemView.width.toFloat() / 2)
////        viewHolder.itemView.translationX = -translationX
//        holder?.foreground?.translationX = -translationX
//        holder?.foreground?.translationY = dY

//        when (actionState){
//            ItemTouchHelper.ACTION_STATE_SWIPE -> {
////                if (isActive) {
//                    holder?.foreground?.translationX = dX
//                    holder?.foreground?.translationY = dY
////                }else {
////                }
//            }
////            ItemTouchHelper.ANIMATION_TYPE_SWIPE_SUCCESS -> Log.e("testLog", "success")
////            ItemTouchHelper.ANIMATION_TYPE_SWIPE_CANCEL -> Log.e("testLog", "cancel")
//            else -> Log.e("testLog", "else")
//        super.onChildDraw(c, recyclerView, viewHolder, -translationX, dY, actionState, isActive)
//        }
    }

//    ;
//
//        private void drawButton(Canvas c, View itemView, List<MyButton> buffer, int pos, float translationX) {
//        float right = itemView.getRight();
//        float dButtonWidth = 200;
////        if (zzz){
//            zzz = false;
//            Paint p = new Paint();
//            p.setColor(Color.parseColor("#FF0055"));
//            c.drawRect(new RectF(itemView.getLeft(), itemView.getTop(), itemView.getWidth(), itemView.getBottom()), p);
//            for (MyButton button : buffer){
//                float left = right - dButtonWidth;
//                button.onDraw(c, new RectF(left, itemView.getTop(), right, itemView.getBottom()), pos);
//                right = left;
//            }
////        }
//
//
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

//    private fun drawableToBitmap(d: Drawable?): Bitmap? {
//        if (d is BitmapDrawable) {
//            return d.bitmap
//        }
//        val bitmap = Bitmap.createBitmap(
//            d!!.intrinsicWidth,
//            d.intrinsicHeight, Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(bitmap)
//        d.setBounds(0, 0, canvas.width, canvas.height)
//        d.draw(canvas)
//        return bitmap
//    }

    class GestureListener : SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
//            for (button in buttonList) {
//                if (button.onClick(e.x, e.y)) {
//                    break
//                }
//            }
            return true
        }
    }

    companion object {

    }

}
