package com.sethkroger.breakoidski.engine;

import android.view.MotionEvent;

/**
 * Handles user input for the game engine.
 */
public class Input {
    private static float touchX;
    private static float touchY;

    /**
     * Get the x-coordinate of the last touched position of the game view.
     * @return x-coordinate of the last touched position of the game view.
     */
    public static float getTouchX() {
        return touchX;
    }

    /**
     * Get the y-coordinate of the last touched position of the game view.
     * @return y-coordinate of the last touched position of the game view.
     */
    public static float getTouchY() {
        return touchY;
    }


    public static boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE || event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            touchX = event.getX();
            touchY = event.getY();

            return true;
        }
        return false;
    }
}