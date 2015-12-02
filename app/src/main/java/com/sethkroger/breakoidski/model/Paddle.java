package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.sethkroger.breakoidski.engine.GameObject;
import com.sethkroger.breakoidski.engine.Input;

/**
 * Breakout paddle game object.
 */
public class Paddle extends GameObject {
    public static final float HALF_PADDLE_WIDTH = 30.0f;
    public static final float HALF_PADDLE_HEIGHT = 5.0f;
    public static final int PADDLE_COLOR = 0xff336699;

    private RectF mPaddleShape;

    /**
     * Creates a Paddle with default size and styling.
     */
    public Paddle() {
        super();
        mPaddleShape = new RectF(-HALF_PADDLE_WIDTH, -HALF_PADDLE_HEIGHT, HALF_PADDLE_WIDTH, HALF_PADDLE_HEIGHT);
        mPaint.setColor(PADDLE_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * Set the paddle horizontal position of the paddle on the game view.
     * This is meant as the primary means of moving the paddle object during the game
     * since the paddle object is meant to side horizontally only.
     */
    public void setPosition(float x) {
        mMatrix.setTranslate(x, mYpos);
    }

    /**
     * Performs the periodic update as required by the game engine.
     */
    @Override
    public void update() {
        setPosition(Input.getTouchX());
    }

    /**
     * Draw the paddle at its current location.
     *
     * @param canvas The canvas object to draw to.
     */
    @Override
    public void draw(Canvas canvas) {
        RectF paddle = new RectF();
        mMatrix.mapRect(paddle, mPaddleShape);
        canvas.drawRect(paddle, mPaint);
    }
}
