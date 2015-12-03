package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.sethkroger.breakoidski.engine.GameObject;

/**
 * Created by Seth on 12/3/2015.
 */
public class Brick extends GameObject {
    public static final float BRICK_HALF_WIDTH = 40.0f;
    public static final float BRICK_HALF_HEIGHT = 20.0f;
    public static final int DEFAULT_BRICK_COLOR = 0xffee2233;

    private RectF mBrickShape;

    /**
     * Creates a Paddle with default size and styling.
     */
    public Brick() {
        this(BRICK_HALF_WIDTH, BRICK_HALF_HEIGHT, DEFAULT_BRICK_COLOR);
    }

    public Brick(float x, float y, int color) {
        super();
        mBrickShape = new RectF(-BRICK_HALF_WIDTH, -BRICK_HALF_HEIGHT, BRICK_HALF_WIDTH, BRICK_HALF_HEIGHT);
        setPosition(x,y);
        recomputeBounds();
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void recomputeBounds() {
        mBoundingRect = mBrickShape;
        mBoundingRect = super.getBoundingRect();
    }


    public void setColor(int color) {
        mPaint.setColor(color);
    }


    @Override
    public void setPosition(PointF position) {
        super.setPosition(position);
        recomputeBounds();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        recomputeBounds();
    }

    @Override
    public RectF getBoundingRect() {
        return mBoundingRect;
    }

    /**
     * Performs the periodic update as required by the game engine.
     */
    @Override
    public void update() {
        // Bricks are stationary so override to about unnecessary computation.
    }

    /**
     * Draw the paddle at its current location.
     *
     * @param canvas The canvas object to draw to.
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(mBoundingRect, mPaint);
    }

}
