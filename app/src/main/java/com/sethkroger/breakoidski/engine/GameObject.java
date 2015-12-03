package com.sethkroger.breakoidski.engine;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Seth on 12/1/2015.
 */
public class GameObject {
    protected Matrix mMatrix = new Matrix();
    protected PointF mPosition = new PointF(0.0f, 0.0f);
    protected PointF mVelocity = new PointF(0.0f, 0.0f);
    protected RectF mBoundingRect;
    protected Paint mPaint = new Paint();

    public GameObject() {
    }

    public PointF getPosition() {
        return mPosition;
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

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setPosition(PointF position) {
        mPosition = position;
        mMatrix.setTranslate(position.x, position.y);
    }

    public PointF getVelocity() {
        return mVelocity;
    }

    public void setVelocity(PointF velocity) {
        mVelocity = velocity;
    }

    public void setVeloicty(float vx, float vy) {
        mVelocity.x = vx;
        mVelocity.y = vy;
    }

    public RectF getBoundingRect() {
        if (mBoundingRect != null) {
            RectF transformedBoundingRect = new RectF();
            mMatrix.mapRect(transformedBoundingRect, mBoundingRect);
            return transformedBoundingRect;
        } else {
            return null;
        }
    }

    public void setBoundingRect(RectF boundingRect) {
        mBoundingRect = boundingRect;
    }

    /**
     * Perform initialization on object that need to be performed on @see GameskiEngine.init()
     * This method is for any initializations can't occur in the constructor because you need
     * properties of the engine or scene.  Does nothing by default.
     */
    public void init() {

    }

    /**
     * Update the game object for every frame.
     * By default, updates the position by the current velocity.
     */
    public void update() {
        float timeDelta = GameskiEngine.getFrameTimeDelta();
        setPosition(mPosition.x + mVelocity.x * timeDelta, mPosition.y + mVelocity.y * timeDelta);
    }

    /**
     * Draw the game object.
     * The default implementation does nothing.  Should be overridden by descendant to draw itself
     * if it is an object to be drawn on screen.
     *
     * @param canvas The android.graphics.Canvas object to draw to.
     */
    public void draw(Canvas canvas) {}

    /**
     * Called by game engine with one GameObject collides with another in the scene.
     * The game engine periodially checks whether GameObject have collided with each
     * other.  This method is called to handle the collison situations.  This method
     * will be called twice for each collision on both colliding objects.
     *
     * @param other The GameObject this one has collided with.
     */
    public void onCollision(GameObject other) {

    }
}
