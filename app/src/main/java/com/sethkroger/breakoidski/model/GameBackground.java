package com.sethkroger.breakoidski.model;

import android.graphics.Canvas;

import com.sethkroger.breakoidski.engine.GameObject;

/**
 * Created by Seth on 12/2/2015.
 */
public class GameBackground extends GameObject {
    public static final int BACKGROUND_COLOR = 0xff222222;

    @Override
    public void update() {}

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(BACKGROUND_COLOR);
    }
}
