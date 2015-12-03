package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.sethkroger.breakoidski.engine.GameObject;
import com.sethkroger.breakoidski.engine.GameskiEngine;

/**
 * Created by Seth on 12/1/2015.
 */
public class Ball extends GameObject {
    public static final float BALL_SIZE = 10.0f;
    public static final float BALL_SPEED = 100.0f;
    public static final int BALL_COLOR = 0xffee88ee;
    public static final double DEG_TO_RADIAN = (2.0 * Math.PI / 360.0);

    public static final float MAX_PADDLE_ANGLE = (float) (60.0 * DEG_TO_RADIAN);
    public static final float MIN_PADDLE_ANGLE = (float) (20.0 * DEG_TO_RADIAN);
    private static final float DEBUG_VELOCITY_ANGLE = (float) (45.0 * DEG_TO_RADIAN);

    public Ball() {
        mPaint.setColor(BALL_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        setTrajectory(DEBUG_VELOCITY_ANGLE);
    }

    private void setTrajectory(float angle) {
        mVelocity.x = (float) (Math.cos(angle) * BALL_SPEED);
        mVelocity.y = (float) (-Math.sin(angle) * BALL_SPEED);
    }

    @Override
    public void update() {
        //make ball bounce off boundary of surface.
        float height = GameskiEngine.getViewportHeight();
        float width = GameskiEngine.getViewportWidth();

        if (mPosition.x <= BALL_SIZE || mPosition.x >= width- BALL_SIZE) {
            mVelocity.x = -mVelocity.x;
        }
        if (mPosition.y <= BALL_SIZE || mPosition.y >= height - BALL_SIZE) {
            mVelocity.y = -mVelocity.y;
        }

        super.update();
    }

    public void onCollision(GameObject other) {
        if (other instanceof Paddle) {
            PointF paddlePos = other.getPosition();
            float distanceFromCenter = (mPosition.x - paddlePos.x) / Paddle.HALF_PADDLE_WIDTH;
            float newAngle = MIN_PADDLE_ANGLE + (MAX_PADDLE_ANGLE - MIN_PADDLE_ANGLE) * distanceFromCenter;
            setTrajectory(newAngle);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mPosition.x, mPosition.y, BALL_SIZE, mPaint);
    }
}
