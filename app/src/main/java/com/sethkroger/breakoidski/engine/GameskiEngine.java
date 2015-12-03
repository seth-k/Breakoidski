package com.sethkroger.breakoidski.engine;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.List;

/**
 * Central class for a rudimentary 2d game engine.
 */
public class GameskiEngine {

    public static final int MINIMUM_FRAME_TIME = 16;
    private long lastFrameTime;
    private float mTimeDelta;

    private SurfaceHolder mViewPort;
    private Rect mViewportDimens;
    private List<GameObject> mSceneObjects;

    private boolean isRunning = false;

    private Runnable mUpdateFrame = new Runnable() {
        @Override
        public void run() {
            while (isRunning) {
                Canvas canvas = null;
                long now = System.currentTimeMillis();
                mTimeDelta = (float) ((now - lastFrameTime) / 1000.0);
                lastFrameTime = now;

                canvas = mViewPort.lockCanvas();
                for (GameObject go: mSceneObjects) {
                    go.update();
                    go.draw(canvas);
                }
                mViewPort.unlockCanvasAndPost(canvas);

//                long sleepTime = MINIMUM_FRAME_TIME - (lastFrameTime - previousFrameTime);
//                Log.d("EngineSleepTime", String.valueOf(lastFrameTime - previousFrameTime));
//                if (sleepTime > 0) {
//                    try {
//                        Thread.sleep(sleepTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    };


    private Thread mUpdateThread = new Thread(mUpdateFrame);
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
        return ourInstance.mTimeDelta;
    }

    /**
     * Initialize the game engine with a scene of game object and a view to render to.
     * @param viewport The SurfaceHolder object of the game display view
     * @param scene A collection of GameObjects that make up the game.
     */
    public void init(SurfaceHolder viewport, List<GameObject> scene) {
        mViewPort = viewport;
        mSceneObjects = scene;
        validateEngineState();
        mViewportDimens = viewport.getSurfaceFrame();
        for(GameObject go: scene) {
            go.init();
        }
    }

    /**
     * Start running the game engine
     */
    public void start() {
        validateEngineState();
        if (!isRunning) {
            lastFrameTime = System.currentTimeMillis();
            isRunning = true;
            mUpdateThread.start();
        }
    }

    private void validateEngineState() {
        if (mViewPort == null || mSceneObjects == null) {
            throw new IllegalStateException("Game Engine must be initialized with a Scene and a Viewport before running.");
        }
    }
    /**
     * Stop the game engine.
     */

    public void stop() {
        boolean retry = true;
        isRunning = false;
        while (retry) {
            try {
                mUpdateThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }


    public static int getViewportHeight() {
        return ourInstance.mViewportDimens.height();
    }

    public static int getViewportWidth() {
        return ourInstance.mViewportDimens.width();
    }
}
