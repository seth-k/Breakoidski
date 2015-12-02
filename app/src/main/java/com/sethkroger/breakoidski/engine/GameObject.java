package com.sethkroger.breakoidski.engine;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Seth on 12/1/2015.
 */
public abstract class GameObject {
    protected Matrix mMatrix = new Matrix();
    protected PointF mPosition = new PointF(0.0f, 0.0f);
    protected PointF mVelocity = new PointF(0.0f, 0.0f);
    protected PointF mAccel = new PointF(0.0f, 0.0f);
    protected Paint mPaint = new Paint();

    public GameObject() {
    }

    /**
     * Put the paddle at the screen position (x,y)
     *
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     */
    public void setPosition(float x, float y) {
        mMatrix.setTranslate(x, y);
        mPosition.x = x;
        mPosition.y = y;
    }

    /**
     * Performs the periodic update as required by the game engine.
     */
    public abstract void update();

    /**
     * Draw the paddle at its current location.
     *
     * @param canvas The android.graphics.Canvas object to draw to.
     */
    public abstract void draw(Canvas canvas);
}
