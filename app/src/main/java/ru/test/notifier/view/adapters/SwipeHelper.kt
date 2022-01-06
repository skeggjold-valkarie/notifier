package ru.test.notifier.view.adapters

import android.graphics.*
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SwipeHelper(
    private val recyclerView: RecyclerView,
    private val listener: ItemTouchListener
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE,
    ItemTouchHelper.LEFT
) {
    private val swipeThresholdRatio = 0.5f
    private val swipeThreshold = 0f

    init {
//        recyclerView.itemAnimator = Slide
    }

    override fun onMove(view: RecyclerView, holder: ViewHolder, target: ViewHolder): Boolean {
        Log.e("testLog", "onMove ");
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
        return 0.3f
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
//            else -> Log.e("testLog", "else")
//     super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isActive)
//        }
    }

    companion object {

    }

}
