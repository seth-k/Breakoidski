package com.sethkroger.breakoidski.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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
        mHolder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE || event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mTouchX = event.getX();
            mTouchY = event.getY();
            drawDot();
            return true;
        }
        return false;
    }

    private void drawDot() {
        Canvas canvas = mHolder.lockCanvas();
        Paint paint = new Paint();
        paint.setStrokeWidth(5.0f);
        paint.setColor(0xff336699);

        canvas.drawARGB(255, 0, 0, 0);
        canvas.drawCircle(mTouchX, mTouchY, 20.0f, paint);

        mHolder.unlockCanvasAndPost(canvas);
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
