package com.sethkroger.breakoidski.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * The Custom view that holds the main game canvas
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Context mContext;
    private SurfaceHolder mHolder;

    private float mTouchX = 0.0f;
    private float mTouchY = 0.0f;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mHolder = getHolder();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            mTouchX = event.getX();
            mTouchY = event.getY();
            return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
