package ru.test.notifier.ui.adapters

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import ru.test.notifier.ui.extensions.AnimationVector

class ItemTouchHelper(
    private val listener: ItemTouchListener
): ItemDecoration(), OnChildAttachStateChangeListener  {

    private var recyclerView: RecyclerView? = null
    private var velocityTracker: VelocityTracker? = null
    private var touchSlop = 0
    private var gestureListener: GestureListener? = null
    private var gestureDetector: GestureDetectorCompat? = null

    private val activeItems = mutableListOf<ActiveItem>()

    private var viewHolder: ViewHolder? = null
    private var view: View? = null

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        when {
            this.recyclerView === recyclerView -> return
            this.recyclerView != null -> destroyCallbacks()
        }
        this.recyclerView = recyclerView
        setupCallbacks()
    }

    private fun setupCallbacks() {
        recyclerView?.context?.let{ touchSlop = ViewConfiguration.get(it).scaledTouchSlop }
        recyclerView?.addItemDecoration(this)
        recyclerView?.addOnItemTouchListener(mOnItemTouchListener)
        recyclerView?.addOnChildAttachStateChangeListener(this)
        startGestureDetection()
    }

    private fun destroyCallbacks() {
        recyclerView?.removeItemDecoration(this)
        recyclerView?.removeOnItemTouchListener(mOnItemTouchListener)
        recyclerView?.removeOnChildAttachStateChangeListener(this)
        releaseVelocityTracker()
        stopGestureDetection()
    }

    private fun startGestureDetection() {
        gestureListener = GestureListener()
        recyclerView?.context?.let { gestureDetector = GestureDetectorCompat(it, gestureListener) }
    }

    private fun stopGestureDetection() {
        gestureDetector = null
        gestureListener = null
    }

    @SuppressLint("Recycle")
    fun obtainVelocityTracker() {
        velocityTracker?.recycle()
        velocityTracker = VelocityTracker.obtain()
    }

    private fun releaseVelocityTracker() {
        velocityTracker?.recycle()
        velocityTracker = null
    }

    private val mOnItemTouchListener = object : OnItemTouchListener {
        override fun onInterceptTouchEvent( view: RecyclerView, event: MotionEvent ): Boolean {
            gestureDetector?.onTouchEvent(event)
//            Log.d("testLog", "intercept: x:" + event.x + ",y:" + event.y + ", " + event )
            when(event.actionMasked){
                MotionEvent.ACTION_DOWN -> {}
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {}
                else -> {}
            }
//            if (action == MotionEvent.ACTION_DOWN) {
//                mActivePointerId = event.getPointerId(0)
//                mInitialTouchX = event.x
//                mInitialTouchY = event.y
//                obtainVelocityTracker()
//                if (mSelected == null) {
//                    val animation: RecoverAnimation = findAnimation(event)
//                    if (animation != null) {
//                        mInitialTouchX -= animation.mX
//                        mInitialTouchY -= animation.mY
//                        endRecoverAnimation(animation.mViewHolder, true)
//                        if (mPendingCleanup.remove(animation.mViewHolder.itemView)) {
//                            mCallback.clearView(mRecyclerView, animation.mViewHolder)
//                        }
//                        select(animation.mViewHolder, animation.mActionState)
//                        updateDxDy(event, mSelectedFlags, 0)
//                    }
//                }
//            } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//                mActivePointerId = ItemTouchHelper.ACTIVE_POINTER_ID_NONE
//                select(null, ItemTouchHelper.ACTION_STATE_IDLE)
//            } else if (mActivePointerId != ItemTouchHelper.ACTIVE_POINTER_ID_NONE) {
//                // in a non scroll orientation, if distance change is above threshold, we
//                // can select the item
//                val index = event.findPointerIndex(mActivePointerId)
//                if (ItemTouchHelper.DEBUG) {
//                    Log.d(
//                        ItemTouchHelper.TAG,
//                        "pointer index $index"
//                    )
//                }
//                if (index >= 0) {
//                    checkSelectForSwipe(action, event, index)
//                }
//            }
//            if (mVelocityTracker != null) {
//                mVelocityTracker!!.addMovement(event)
//            }
            return true
        }

        override fun onTouchEvent(recyclerView: RecyclerView, event: MotionEvent) {
            gestureDetector?.onTouchEvent(event)
            Log.d("testLog", "event: x:" + event.x + ",y:" + event.y + ", " + event )
//            if (ItemTouchHelper.DEBUG) {
//                Log.d("testLog",
//                    "on touch: x:$mInitialTouchX,y:$mInitialTouchY, :$event"
//                )
//            }
//            if (mVelocityTracker != null) {
//                mVelocityTracker!!.addMovement(event)
//            }
//            if (mActivePointerId == ItemTouchHelper.ACTIVE_POINTER_ID_NONE) {
//                return
//            }
//            val action = event.actionMasked
//            val activePointerIndex = event.findPointerIndex(mActivePointerId)
//            if (activePointerIndex >= 0) {
//                checkSelectForSwipe(action, event, activePointerIndex)
//            }
//            val viewHolder: RecyclerView.ViewHolder = mSelected ?: return
//            when (action) {
//                MotionEvent.ACTION_MOVE -> {
//
//                    // Find the index of the active pointer and fetch its position
//                    if (activePointerIndex >= 0) {
//                        updateDxDy(event, mSelectedFlags, activePointerIndex)
//                        moveIfNecessary(viewHolder)
//                        mRecyclerView!!.removeCallbacks(mScrollRunnable)
//                        mScrollRunnable.run()
//                        mRecyclerView!!.invalidate()
//                    }
//                }
//                MotionEvent.ACTION_CANCEL -> {
//                    if (mVelocityTracker != null) {
//                        mVelocityTracker!!.clear()
//                    }
//                    select(null, ItemTouchHelper.ACTION_STATE_IDLE)
//                    mActivePointerId = ItemTouchHelper.ACTIVE_POINTER_ID_NONE
//                }
//                MotionEvent.ACTION_UP -> {
//                    select(null, ItemTouchHelper.ACTION_STATE_IDLE)
//                    mActivePointerId = ItemTouchHelper.ACTIVE_POINTER_ID_NONE
//                }
//                MotionEvent.ACTION_POINTER_UP -> {
//                    val pointerIndex = event.actionIndex
//                    val pointerId = event.getPointerId(pointerIndex)
//                    if (pointerId == mActivePointerId) {
//                        // This was our active pointer going up. Choose a new
//                        // active pointer and adjust accordingly.
//                        val newPointerIndex = if (pointerIndex == 0) 1 else 0
//                        mActivePointerId = event.getPointerId(newPointerIndex)
//                        updateDxDy(event, mSelectedFlags, pointerIndex)
//                    }
//                }
//            }
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            Log.d("testLog", "disallowIntercept: $disallowIntercept ")
        }
    }


    private class GestureListener: SimpleOnGestureListener() {

        var shouldReactToLongPress = true

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.e("testLog", "onSingleTapUp $e ")
            return super.onSingleTapUp(e)
        }

        override fun onLongPress(e: MotionEvent) {
            Log.e("testLog", "onLongPress $e ")
//            if (!shouldReactToLongPress) {
//                return
//            }
//            val child: View = findChildView(e)
//            if (child != null) {
//                val vh: RecyclerView.ViewHolder = mRecyclerView.getChildViewHolder(child)
//                if (vh != null) {
//                    if (!mCallback.hasDragFlag(mRecyclerView, vh)) {
//                        return
//                    }
//                    val pointerId = e.getPointerId(0)
//                    // Long press is deferred.
//                    // Check w/ active pointer id to avoid selecting after motion
//                    // event is canceled.
//                    if (pointerId == mActivePointerId) {
//                        val index = e.findPointerIndex(mActivePointerId)
//                        val x = e.getX(index)
//                        val y = e.getY(index)
//                        mInitialTouchX = x
//                        mInitialTouchY = y
//                        mDy = 0f
//                        mDx = mDy
//                        if (ItemTouchHelper.DEBUG) {
//                            Log.d(
//                                ItemTouchHelper.TAG,
//                                "onlong press: x:$mInitialTouchX,y:$mInitialTouchY"
//                            )
//                        }
//                        if (mCallback.isLongPressDragEnabled()) {
//                            select(vh, ItemTouchHelper.ACTION_STATE_DRAG)
//                        }
//                    }
//                }
//            }
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, dX: Float, dY: Float): Boolean {
            Log.e("testLog", "onScroll $e1 | $e2 | $dX | $dX ")
            return false
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, vX: Float, vY: Float): Boolean {
            Log.e("testLog", "onFling $e1 | $e2 | $vX | $vX ")
            return false
        }

        override fun onShowPress(e: MotionEvent) {
//            Log.e("testLog", "onShowPress $e ")
        }

        override fun onDown(e: MotionEvent): Boolean {
//            Log.e("testLog", "onDown $e ")
            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
//            Log.e("testLog", "onDoubleTap $e ")
            return false
        }

        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
//            Log.e("testLog", "onDoubleTapEvent $e ")
            return false
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
//            Log.e("testLog", "onSingleTapConfirmed $e ")
            return false
        }

        override fun onContextClick(e: MotionEvent): Boolean {
//            Log.e("testLog", "onContextClick $e ")
            return false
        }

    }

    override fun onChildViewAttachedToWindow(view: View) {
        Log.e("testLog", "onChildViewAttachedToWindow ${view::class.java.canonicalName} ")
    }

    override fun onChildViewDetachedFromWindow(view: View) {
        Log.e("testLog", "onChildViewDetachedFromWindow ${view::class.java.canonicalName} ")
    }

//
//    override fun select(selected: RecyclerView.ViewHolder?, actionState: Int) {
//        super.select(selected, actionState)
//    }
//
//
//    private fun select2(){
//        if (selected === mSelected && actionState == mActionState) {
//            return
//        }
//        mDragScrollStartTimeInMs = Long.MIN_VALUE
//        val prevActionState = mActionState
//        // prevent duplicate animations
//        // prevent duplicate animations
//        endRecoverAnimation(selected, true)
//        mActionState = actionState
//        if (actionState == ACTION_STATE_DRAG) {
//            requireNotNull(selected) { "Must pass a ViewHolder when dragging" }
//
//            // we remove after animation is complete. this means we only elevate the last drag
//            // child but that should perform good enough as it is very hard to start dragging a
//            // new child before the previous one settles.
//            mOverdrawChild = selected.itemView
//            addChildDrawingOrderCallback()
//        }
//        val actionStateMask = ((1 shl DIRECTION_FLAG_COUNT + DIRECTION_FLAG_COUNT * actionState)
//                - 1)
//        var preventLayout = false
//
//        if (mSelected != null) {
//            val prevSelected = mSelected
//            if (prevSelected.itemView.parent != null) {
//                val swipeDir =
//                    if (prevActionState == ACTION_STATE_DRAG) 0 else swipeIfNecessary(prevSelected)
//                releaseVelocityTracker()
//                // find where we should animate to
//                val targetTranslateX: Float
//                val targetTranslateY: Float
//                val animationType: Int
//                when (swipeDir) {
//                    LEFT, RIGHT, START, END -> {
//                        targetTranslateY = 0f
//                        targetTranslateX = Math.signum(mDx) * mRecyclerView.width
//                    }
//                    UP, DOWN -> {
//                        targetTranslateX = 0f
//                        targetTranslateY = Math.signum(mDy) * mRecyclerView.height
//                    }
//                    else -> {
//                        targetTranslateX = 0f
//                        targetTranslateY = 0f
//                    }
//                }
//                animationType = if (prevActionState == ACTION_STATE_DRAG) {
//                    ANIMATION_TYPE_DRAG
//                } else if (swipeDir > 0) {
//                    ANIMATION_TYPE_SWIPE_SUCCESS
//                } else {
//                    ANIMATION_TYPE_SWIPE_CANCEL
//                }
//                getSelectedDxDy(mTmpPosition)
//                val currentTranslateX = mTmpPosition[0]
//                val currentTranslateY = mTmpPosition[1]
//                val rv: RecoverAnimation = object : RecoverAnimation(
//                    prevSelected, animationType,
//                    prevActionState, currentTranslateX, currentTranslateY,
//                    targetTranslateX, targetTranslateY
//                ) {
//                    fun onAnimationEnd(animation: Animator?) {
//                        super.onAnimationEnd(animation)
//                        if (mOverridden) {
//                            return
//                        }
//                        if (swipeDir <= 0) {
//                            // this is a drag or failed swipe. recover immediately
//                            mCallback.clearView(mRecyclerView, prevSelected)
//                            // full cleanup will happen on onDrawOver
//                        } else {
//                            // wait until remove animation is complete.
//                            mPendingCleanup.add(prevSelected.itemView)
//                            mIsPendingCleanup = true
//                            if (swipeDir > 0) {
//                                // Animation might be ended by other animators during a layout.
//                                // We defer callback to avoid editing adapter during a layout.
//                                postDispatchSwipe(this, swipeDir)
//                            }
//                        }
//                        // removed from the list after it is drawn for the last time
//                        if (mOverdrawChild === prevSelected.itemView) {
//                            removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView)
//                        }
//                    }
//                }
//                val duration = mCallback.getAnimationDuration(
//                    mRecyclerView, animationType,
//                    targetTranslateX - currentTranslateX, targetTranslateY - currentTranslateY
//                )
//                rv.setDuration(duration)
//                mRecoverAnimations.add(rv)
//                rv.start()
//                preventLayout = true
//            } else {
//                removeChildDrawingOrderCallbackIfNecessary(prevSelected.itemView)
//                mCallback.clearView(mRecyclerView, prevSelected)
//            }
//            mSelected = null
//        }
//        if (selected != null) {
//            mSelectedFlags =
//                (mCallback.getAbsoluteMovementFlags(mRecyclerView, selected) and actionStateMask
//                        shr mActionState * DIRECTION_FLAG_COUNT)
//            mSelectedStartX = selected.itemView.left.toFloat()
//            mSelectedStartY = selected.itemView.top.toFloat()
//            mSelected = selected
//            if (actionState == ACTION_STATE_DRAG) {
//                mSelected.itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
//            }
//        }
//        val rvParent = mRecyclerView.parent
//        rvParent?.requestDisallowInterceptTouchEvent(mSelected != null)
//        if (!preventLayout) {
//            mRecyclerView.layoutManager!!.requestSimpleAnimationsInNextLayout()
//        }
//        mCallback.onSelectedChanged(mSelected, mActionState)
//        mRecyclerView.invalidate()
//    }

    private class ActiveItem(
        val viewHolder: ViewHolder,
        val view: View?,
        val vector: AnimationVector<Float>
    ): Animator.AnimatorListener {

        private val valueAnimator = ValueAnimator.ofFloat(0f, 1f);

        override fun onAnimationStart(animation: Animator?) {
            viewHolder.setIsRecyclable(false)
            valueAnimator.start()
        }

        override fun onAnimationEnd(animation: Animator?) {
            viewHolder.setIsRecyclable(true)
            Log.e("testLog", "onAnimationEnd")
        }

        override fun onAnimationCancel(animation: Animator?) {
            Log.e("testLog", "onAnimationCancel")
        }

        override fun onAnimationRepeat(animation: Animator?) {
            Log.e("testLog", "onAnimationRepeat")
        }
    }


    companion object{
        private const val ACTION_STATE_IDLE = 0
        private const val ACTION_STATE_SWIPE = 1
        private const val ACTION_STATE_DRAG = 2

        private const val ANIMATION_TYPE_SWIPE_SUCCESS = 0
        private const val ANIMATION_TYPE_SWIPE_MIDDLE = 1
        private const val ANIMATION_TYPE_SWIPE_CANCEL = 2
    }
}