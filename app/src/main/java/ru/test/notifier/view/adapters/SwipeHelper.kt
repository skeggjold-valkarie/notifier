package ru.test.notifier.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.test.notifier.view.screens.EventsFragment
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class SwipeHelper(
    private val recyclerView: RecyclerView,
    private val listener: SwipeListener
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT
) {
    private val swipeThresholdRatio = 0.5f
    private val swipeThreshold = 0f

    override fun onMove(view: RecyclerView, holder: ViewHolder, target: ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        Log.e("testLog", "direction $direction ");
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
        return 0.7f
    }

    override fun onChildDraw(
        c: Canvas,
        rv: RecyclerView,
        viewHolder: ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isActive: Boolean
    ) {
        val holder = viewHolder as? EventsAdapter.ItemViewHolder
        val view = holder?.itemView
//        holder?.foreground?.setY(holder!!.itemView.getTop().toFloat());
//        holder?.background?.visibility = if (isCurrentlyActive) View.VISIBLE else View.GONE
        Log.e("testLog", "state $actionState | $isActive | $dX | $dY")
        val translationX: Float = minOf(-dX, viewHolder.itemView.width.toFloat() / 2)
//        viewHolder.itemView.translationX = -translationX
        holder?.foreground?.translationX = -translationX
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
//            else -> Log.e("testLog", "else") //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isActive)
//        }
    }

    companion object {

    }

}
