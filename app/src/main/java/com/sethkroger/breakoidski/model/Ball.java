package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.sethkroger.breakoidski.engine.GameObject;
import com.sethkroger.breakoidski.engine.GameskiEngine;

import java.util.BitSet;

/**
 * Created by Seth on 12/1/2015.
 */
public class Ball extends GameObject {
    public static final float BALL_SIZE = 10.0f;
    public static final float BALL_SPEED = 200.0f;
    public static final int BALL_COLOR = 0xffee88ee;
    public static final double DEG_TO_RADIAN = (2.0 * Math.PI / 360.0);

    public static final float MAX_PADDLE_ANGLE = (float) (60.0 * DEG_TO_RADIAN);
    public static final float MIN_PADDLE_ANGLE = (float) (20.0 * DEG_TO_RADIAN);
    private static final float DEBUG_VELOCITY_ANGLE = (float) (45.0 * DEG_TO_RADIAN);
    private float mViewportHeight;
    private float mViewportWidth;

    public Ball() {
        mBoundingRect = new RectF(-BALL_SIZE, -BALL_SIZE, BALL_SIZE, BALL_SIZE);
        mPaint.setColor(BALL_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        setTrajectory(DEBUG_VELOCITY_ANGLE);
    }

    private void setTrajectory(float angle) {
        mVelocity.y = (float) (-Math.cos(angle) * BALL_SPEED);
        mVelocity.x = (float) (Math.sin(angle) * BALL_SPEED);
    }

    @Override
    public void init() {
        mViewportHeight = GameskiEngine.getViewportHeight() - BALL_SIZE;
        mViewportWidth = GameskiEngine.getViewportWidth() - BALL_SIZE;
    }

    @Override
    public void update() {
        //make ball bounce off boundary of surface.

        if ((mPosition.x <= BALL_SIZE && Math.signum(mVelocity.x) < 0.0f)
                || (mPosition.x >= mViewportWidth && Math.signum(mVelocity.x) > 0.0f)) {
            mVelocity.x = -mVelocity.x;
        }
        if ((mPosition.y <= BALL_SIZE && Math.signum(mVelocity.y) < 0.0f)
                || (mPosition.y >= mViewportHeight && Math.signum(mVelocity.y) > 0.0f) ) {
            mVelocity.y = -mVelocity.y;
        }
        GameskiEngine.checkCollisions(this);
        super.update();
    }

    public void onCollision(GameObject other) {
        if (other instanceof Paddle && Math.signum(mVelocity.y) > 0.0f) {
            PointF paddlePos = other.getPosition();
            float distanceFromCenter = (mPosition.x - paddlePos.x) / Paddle.HALF_PADDLE_WIDTH;
            float newAngle = Math.signum(distanceFromCenter) * MIN_PADDLE_ANGLE
                    + (MAX_PADDLE_ANGLE - MIN_PADDLE_ANGLE) * distanceFromCenter;
            setTrajectory(newAngle);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mPosition.x, mPosition.y, BALL_SIZE, mPaint);
    }
}
