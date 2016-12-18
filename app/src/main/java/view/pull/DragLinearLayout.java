package view.pull;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by zpw10018 on 2016/12/16.
 */

public class DragLinearLayout extends LinearLayout {
    static final String TAG = "ZPW_DragLinearLayout";
    static final float FRICTION = 1.5f;

    static final int DRAG_STATE = 0x0;
    static final int RELEASE_STATE = 0x1;
    static final int NOMAL_STATE = 0x2;

    private int mState = NOMAL_STATE;
    private int mLimitDragDownHeight = -1;
    private boolean mDragEnable = true;
    private boolean mIsBeingDragged;

    private float mLastMotionY, mInitialMotionY, mInitialMotionX, mLastMotionX;
    private int mTouchSlop;

    private DragDistanceListener mDragDistanceListener;

    public DragLinearLayout(Context context) {
        super(context);
        init();
    }

    public DragLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DragLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptTouchEvent:----" + "MotionEvent: " + event.getAction());
        if (!mDragEnable || !isCanDragState()) {
            return false;
        }

        final int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsBeingDragged = false;
            return false;
        }

        if (action != MotionEvent.ACTION_DOWN && mIsBeingDragged) {
            return true;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = mInitialMotionY = event.getY();
                mLastMotionX = mInitialMotionX = event.getX();
                mIsBeingDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float y = event.getY(), x = event.getX();
                final float diff, oppositeDiff, absDiff;
                diff = y - mLastMotionY;
                oppositeDiff = x - mLastMotionX;
                absDiff = Math.abs(diff);
                if (absDiff > mTouchSlop && (absDiff > Math.abs(oppositeDiff))) {
                    if (diff >= 1f) {
                        mLastMotionY = y;
                        mLastMotionX = x;
                        mIsBeingDragged = true;

                    } else if (diff <= -1f) {
                        mLastMotionY = y;
                        mLastMotionX = x;
                        mIsBeingDragged = true;

                    }
                }
                break;
        }

        return mIsBeingDragged;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent:----" + "MotionEvent: " + event.getAction());
        if (!isCanDragState()) {
            return true;
        }

        //why
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = mInitialMotionY = event.getY();
                mLastMotionX = event.getX();
                return true;

            case MotionEvent.ACTION_MOVE:
                if (true) {
                    mLastMotionY = event.getY();
                    mLastMotionX = event.getX();
                    dragEvent();
                    return true;
                }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                if (true) {
                    mIsBeingDragged = false;
                    setScroll(0);
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private void init() {
        ViewConfiguration config = ViewConfiguration.get(getContext());
        mTouchSlop = config.getScaledTouchSlop();
    }

    private void dragEvent() {
        final int newScrollValue;
        final int itemDimension;
        final float initialMotionValue, lastMotionValue;

        initialMotionValue = mInitialMotionY;
        lastMotionValue = mLastMotionY;

        newScrollValue = Math.round(Math.min(initialMotionValue - lastMotionValue, 0) / FRICTION);
        setScroll(newScrollValue);
    }

    private void setScroll(int value) {
        scrollTo(0, value);
        if (mDragDistanceListener != null) {
            mDragDistanceListener.onDrag(value);
        }
    }

    private boolean isCanDragState() {
        return mState == NOMAL_STATE;
    }

    public void setDragDistanceListener(DragDistanceListener dragDistanceListener) {
        mDragDistanceListener = dragDistanceListener;
    }

    interface DragDistanceListener {
        void onDrag(int distanceValue);
    }
}
