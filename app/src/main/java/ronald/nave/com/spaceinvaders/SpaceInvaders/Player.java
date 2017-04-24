package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import ronald.nave.com.spaceinvaders.R;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Player
{
    private static Player instance;

    private Paint blue;
    private float x, y, width, height;
    private float speedX;
    private boolean isMoving, isMovingLeft;
    static Context c;
    Bitmap bm;
    private Player(Context ctx)
    {
        c = ctx;

        blue = new Paint();
        width = SpaceInvadersView.screenW * 0.2f;
        height = SpaceInvadersView.screenW * 0.1f;


        bm = BitmapFactory.decodeResource(c.getResources(), R.drawable.ship);
        bm = Bitmap.createScaledBitmap(bm, (int) width, (int) height, false);
        x = (SpaceInvadersView.screenW/ 2) - (width/ 2);
        y = SpaceInvadersView.screenH * 0.8f;

        speedX = 7;
        isMoving = isMovingLeft = false;
    }

    public static Player getInstance(Context ctx)
    {
        if(instance == null) instance = new Player(ctx);

        return instance;
    }

    public float GetX(){return x;}
    public float GetY(){return y;}
    public float GetWidth(){ return width;}
    public float GetHeight(){return  height;}

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bm, x, y, blue);
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
        else if(event.getAction() == MotionEvent.ACTION_UP) isMoving = false;
    }
    private void CollisionWithScreen()
    {
        if(x < 0) x += speedX;
        else if(x + width > SpaceInvadersView.screenW) x -= speedX;
    }
}
