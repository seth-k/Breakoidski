package com.sethkroger.breakoidski.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sethkroger.breakoidski.model.Paddle;

/**
 * The Custom view that holds the main game canvas
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final float PADDLE_TRACK_SCREEN_FRACTION = 0.8f;

    private Context mContext;
    private SurfaceHolder mHolder;

    private float mTouchX = 0.0f;
    private float mTouchY = 0.0f;

    private Paddle mPaddle;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mHolder = getHolder();
        mHolder.addCallback(this);

        mPaddle = new Paddle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE || event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mTouchX = event.getX();
            mTouchY = event.getY();
            mPaddle.setPosition(mTouchX);
            draw();
            return true;
        }
        return false;
    }

    /**
     * Blanks the view and redraws the components.
     */
    public void draw() {
        Canvas canvas = mHolder.lockCanvas();

        // redraw background to erase previous canvas
        canvas.drawARGB(255, 0, 0, 0);

        mPaddle.draw(canvas);

        mHolder.unlockCanvasAndPost(canvas);
    }

    /**
     * Draw a light blue dot at the tracked touch position.
     * Used mainly for debugging purposes to highlight where the input touch coordinates are
     *
     * @param canvas Canvas object to draw the dot on/
     */
    private void drawDot(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(5.0f);
        paint.setColor(0xff336699);

        canvas.drawCircle(mTouchX, mTouchY, 20.0f, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mPaddle.setPosition((int) mTouchX, (int) (height * PADDLE_TRACK_SCREEN_FRACTION));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
