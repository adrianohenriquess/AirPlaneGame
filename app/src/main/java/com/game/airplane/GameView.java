package com.game.airplane;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import com.game.airplane.components.Background;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;

    private boolean isPlaying;

    private int screenX, screenY;

    private float screenRatioX, screenRatioY;

    private Paint paint;

    private Background background1, background2;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.screenX = screenX;
        this.screenY = screenY;

        Log.i("Game","Screen x:.." + this.screenX);
        Log.i("Game","Screen Y:.." + this.screenY);

        this.screenRatioX = 1920f / screenX;
        this.screenRatioY = 1080f / screenY;

        Log.i("Game","Proporção da tela em relação a X:.." + this.screenRatioX);
        Log.i("Game","Proporção da tela em relação a Y:.." + this.screenRatioY);

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        Log.i("Game","O x do background 2 Antes:.." + this.background2.x);
        background2.x = screenX;
        Log.i("Game","O x do background 2 Depois:.." + this.background2.x);
        paint = new Paint();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        //retira de 10 em 10 multiplicado pela porporção da tela do celular
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            Log.i("Game","teste:.." + this.background1.x);
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            Log.i("Game","teste 2:.." + this.background2.x);
            background2.x = screenX;
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
