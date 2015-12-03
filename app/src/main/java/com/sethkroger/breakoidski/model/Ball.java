package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.sethkroger.breakoidski.engine.GameObject;

/**
 * Created by Seth on 12/1/2015.
 */
public class Ball extends GameObject {
    public static final float BALL_SIZE = 10.0f;
    public static final float BALL_SPEED = 5.0f;
    public static final int BALL_COLOR = 0xffee88ee;

    private static final float DEBUG_VELOCITY_ANGLE = (float) (45.0 * (2.0 * Math.PI / 360.0));

    public Ball() {
        mPaint.setColor(BALL_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        mVelocity.x = (float) (Math.cos(DEBUG_VELOCITY_ANGLE) * BALL_SPEED);
        mVelocity.y = (float) (- Math.sin(DEBUG_VELOCITY_ANGLE) * BALL_SPEED);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mPosition.x, mPosition.y, BALL_SIZE, mPaint);
    }
}
