package com.sethkroger.breakoidski.engine;

/**
 * Central class for a rudimentary 2d game engine.
 */
public class GameskiEngine {


    private static GameskiEngine ourInstance = new GameskiEngine();

    public static GameskiEngine getInstance() {
        return ourInstance;
    }

    private GameskiEngine() {
    }

    /**
     * Get the time from the previous frame/update
     * TODO Make a real implementation instead of fake
     * @return Time since the last frame in seconds.
     */
    public static float getFrameTimeDelta() {
        return 1.0f / 60.0f ;
    }
}
