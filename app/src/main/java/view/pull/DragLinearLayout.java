package view.pull;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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

    private int mState;
    private int mLimitDragDownHeight = -1;
    private boolean mDragEnable = true;

    private float mLastMotionY, mInitialMotionY, mLastMotionX;

    public DragLinearLayout(Context context) {
        super(context);
    }

    public DragLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        Log.d(TAG, "onInterceptHoverEvent:----" + "MotionEvent: " + event.getAction());
        if (!mDragEnable || !isCanDragState()) {
            return super.onInterceptHoverEvent(event);
        }
        return true;
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
                break;
//                isBeingDragged = false;
            case MotionEvent.ACTION_MOVE:


        }

        return super.onTouchEvent(event);
    }

    private boolean isCanDragState() {
        return mState == NOMAL_STATE;
    }
}
