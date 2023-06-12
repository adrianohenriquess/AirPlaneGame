package com.game.airplane.components;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.airplane.R;

public class Background {
    public int x = 0;
    public int y = 0;
    public Bitmap background;

    public Background(int screenX, int screenY, Resources resources) {
        background = BitmapFactory.decodeResource(resources, R.drawable.back);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
    }
}
