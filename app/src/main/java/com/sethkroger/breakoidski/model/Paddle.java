package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Breakout paddle game object.
 */
public class Paddle {
    public static final float HALF_PADDLE_WIDTH = 25.0f;
    public static final float HALF_PADDLE_HEIGHT = 2.5f;
    public static final int PADDLE_COLOR = 0xff336699;

    private Matrix mMatrix;
    private float mYpos;
    private RectF mPaddleShape;
    private Paint mPaint;

    /**
     * Creates a Paddle with default size and styling.
     */
    public Paddle() {
        mMatrix = new Matrix();
        mPaddleShape = new RectF(-HALF_PADDLE_WIDTH, -HALF_PADDLE_HEIGHT, HALF_PADDLE_WIDTH, HALF_PADDLE_HEIGHT);
        mPaint = new Paint();
        mPaint.setColor(PADDLE_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * Put the paddle at the screen position (x,y)
     *
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     */
    public void setPosition(int x, int y) {
        mMatrix.setTranslate(x, y);
        mYpos = y;
    }

    /**
     * Set the paddle horizontal position of the paddle on the game view.
     * This is meant as the primary means of moving the paddle object during the game
     * since the paddle object is meant to side horizontally only.
     *
     */
    public void setPosition(float x) {
        mMatrix.setTranslate(x, mYpos);
    }

    /**
     * Performs the periodic update as required by the game engine.
     *
     */
    public void update() {

    }

    /**
     * Draw the paddle at its current location.
     *
     * @param canvas The canvas object to draw to.
     */
    public void draw(Canvas canvas) {
        RectF paddle = new RectF();
        mMatrix.mapRect(paddle, mPaddleShape);
        canvas.drawRect(paddle, mPaint);
    }
}
