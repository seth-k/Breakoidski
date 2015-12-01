package com.sethkroger.breakoidski.model;

import android.graphics.Matrix;
import android.graphics.Rect;

/**
 * Breakout paddle game object.
 */
public class Paddle {
    public static final int PADDLE_WIDTH = 50;
    public static final int PADDLE_HEIGHT = 5;

    private Matrix mMatrix;
    private Rect mPaddleShape;

}
