package com.game.airplane.components;

import static com.game.airplane.GameView.screenRatioX;
import static com.game.airplane.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.airplane.R;

public class Flight {
    public int x, y, width, height, wingCounter = 0;
    public Bitmap flight1, flight2;
    public boolean isGoingUp = false;

    public Flight(int screenY, Resources resources) {
        flight1 = BitmapFactory.decodeResource(resources, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(resources, R.drawable.fly2);

        width = flight1.getWidth();
        height = flight1.getHeight();

        width /= 4;
        height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        flight1 = Bitmap.createScaledBitmap(flight1, width, height, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

    public Bitmap getFlight() {
        if (wingCounter == 0) {
            wingCounter++;
            return flight1;
        }
        wingCounter --;
        return flight2;
    }

}
