package com.sethkroger.breakoidski.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sethkroger.breakoidski.engine.GameObject;
import com.sethkroger.breakoidski.engine.GameskiEngine;
import com.sethkroger.breakoidski.engine.Input;
import com.sethkroger.breakoidski.model.Ball;
import com.sethkroger.breakoidski.model.GameBackground;
import com.sethkroger.breakoidski.model.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Custom view that holds the main game canvas
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private static final float PADDLE_TRACK_SCREEN_FRACTION = 0.8f;

    private Context mContext;
    private SurfaceHolder mHolder;

    private final GameskiEngine mEngine = GameskiEngine.getInstance();

    private Paddle mPaddle = new Paddle();
    private Ball mBall = new Ball();


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mHolder = getHolder();
        mHolder.addCallback(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = Input.onTouchEvent(event);

        return handled;
    }

    private void update() {
        mPaddle.update();
        mBall.update();
    }

    /**
     * Blanks the view and redraws the components.
     */
    public void draw() {
        Canvas canvas = mHolder.lockCanvas();

        // redraw background to erase previous canvas
        canvas.drawARGB(255, 0, 0, 0);

        mPaddle.draw(canvas);
        mBall.draw(canvas);

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

        canvas.drawCircle(Input.getTouchX(), Input.getTouchY(), 20.0f, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        List<GameObject> scene = new ArrayList<>();
        scene.add(new GameBackground());
        scene.add(mPaddle);
        scene.add(mBall);
        mEngine.init(holder, scene);
        mEngine.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mPaddle.setPosition(40, height * PADDLE_TRACK_SCREEN_FRACTION);
        mBall.setPosition(40, height * PADDLE_TRACK_SCREEN_FRACTION);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mEngine.stop();
    }
}
