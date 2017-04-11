package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Player
{
    private static Player instance;

    private Paint blue;
    private float x, y, width, height, speedX;
    private boolean isMoving, isMovingLeft;

    private Player()
    {
        blue = new Paint();
        blue.setARGB(255, 255, 255, 255);

        width = SpaceInvadersView.screenW * 0.2f;
        height = SpaceInvadersView.screenW * 0.03f;
        x = (SpaceInvadersView.screenW/ 2) - (width/ 2);
        y = SpaceInvadersView.screenH * 0.8f;

        speedX = 7;
        isMoving = isMovingLeft = false;
    }

    public static Player getInstance()
    {
        if(instance == null) instance = new Player();

        return instance;
    }

    public float GetX(){return x;}
    public float GetY(){return y;}
    public float GetWidth(){ return width;}
    public float GetHeight(){return  height;}

    public void draw(Canvas canvas)
    {
        canvas.drawRect(x, y, x + width, y + height, blue);
    }

    public void update()
    {
        if(isMoving)
        {
            if(isMovingLeft) x -= speedX;
            else x += speedX;
        }
        CollisionWithScreen();
    }
    public  void preUpdate(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
        {
            isMoving = true;
            isMovingLeft = x > event.getRawX();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            isMoving = false;
        }
    }
    private void CollisionWithScreen()
    {
        if(x < 0) x += speedX;
        else if(x + width > SpaceInvadersView.screenW) x -= speedX;
    }
}
