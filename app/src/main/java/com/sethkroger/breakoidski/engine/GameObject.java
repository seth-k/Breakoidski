package com.sethkroger.breakoidski.engine;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Seth on 12/1/2015.
 */
public abstract class GameObject {
    protected Matrix mMatrix;
    protected float mXpos;
    protected float mYpos;
    protected Paint mPaint;

    public GameObject() {
        mPaint = new Paint();
        mMatrix = new Matrix();
    }

    /**
     * Put the paddle at the screen position (x,y)
     *
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     */
    public void setPosition(int x, int y) {
        mMatrix.setTranslate(x, y);
        mXpos = x;
        mYpos = y;
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
