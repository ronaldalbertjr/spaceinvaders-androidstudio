package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import ronald.nave.com.spaceinvaders.MainActivity;
import ronald.nave.com.spaceinvaders.R;

public class Enemy
{
    private Paint green;
    private float x,y,width, height;
    private boolean removed;

    public Enemy(int i, int j, int columns, Context ctx)
    {
        green = new Paint();
        green.setARGB(255, 255, 255, 0);


        width = (SpaceInvadersView.screenW / columns) - ((SpaceInvadersView.screenW * 0.05f) + 2 * columns) / columns;
        height = SpaceInvadersView.screenH * 0.05f;

        this.x = ((SpaceInvadersView.screenW * 0.05f) + j * width + (j*2)) + 20;
        this.y = (SpaceInvadersView.screenH * 0.05f) + i * height + (i*2);

        removed = false;
    }

    public float GetX(){return x;}
    public float GetY(){return y;}
    public float GetWidth(){return width;}
    public float GetHeight(){return height;}
    public boolean GetRemoved(){return removed;}
    public void SetRemoved(boolean value) {removed = value;}

    public void draw(Canvas canvas, Bitmap bm)
    {
        if(!removed) canvas.drawBitmap(bm, x, y, green);
    }

    public void update(float speedX)
    {
        this.x += speedX;
    }
}
