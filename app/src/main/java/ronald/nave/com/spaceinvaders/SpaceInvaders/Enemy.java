package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy
{
    private Paint green;
    private float x,y,width, height;
    private boolean removed;

    public Enemy(int i, int j, int columns)
    {
        green = new Paint();
        green.setARGB(255, 0, 255, 0);

        width = (SpaceInvadersView.screenW / columns) - ((SpaceInvadersView.screenW * 0.02f) + 2 * columns) / columns;
        height = SpaceInvadersView.screenH * 0.05f;

        this.x = (SpaceInvadersView.screenW * 0.02f) + j * width + (j*2);
        this.y = (SpaceInvadersView.screenH * 0.05f) + i * height + (i*2);

        removed = false;
    }

    public float GetX(){return x;}
    public float GetY(){return y;}
    public float GetWidth(){return width;}
    public float GetHeight(){return height;}
    public boolean GetRemoved(){return removed;}
    public void SetRemoved(boolean value) {removed = value;}

    public void draw(Canvas canvas)
    {
        if(!removed) canvas.drawRect(x, y, x+ width, y+width, green);
    }
}
