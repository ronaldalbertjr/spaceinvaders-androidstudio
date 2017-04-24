package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Bullet
{
    private Paint red;
    private float x, y, radius, speedY;
    private Player player;
    private EnemyManager em;
    public boolean destroyed;
    public boolean playerBullet;

    public Bullet(float xPos, float yPos, boolean bulletType, Context ctx)
    {
        red = new Paint();
        red.setARGB(255, 255, 0, 0);

        playerBullet = bulletType;
        x = xPos;
        y = yPos;
        radius = SpaceInvadersView.screenW * 0.01f;
        speedY = -12f;
        destroyed = false;

        player = Player.getInstance(ctx);
        em = EnemyManager.getInstance(ctx);
    }

    public void draw(Canvas canvas)
    {
        if(!destroyed) canvas.drawCircle(x , y, radius, red);
    }

    public void update()
    {
        if(playerBullet)
        {
            y += speedY;
        }
        else
        {
            y -= speedY;
        }

        Collision();
        CollisionWithScreen();
    }

    private void CollisionWithScreen()
    {
        if(y + radius > SpaceInvadersView.screenH) destroyed = true;
        else if(x + radius > SpaceInvadersView.screenW) destroyed = true;
    }

    private void Collision()
    {
        if(playerBullet)
        {
            for (Enemy e : em.enemies) {
                if (!e.GetRemoved() && x - radius < e.GetX() + e.GetWidth() && x + radius > e.GetX() && y - radius < e.GetY() + e.GetHeight() && y + radius > e.GetY()) {
                    speedY *= -1;
                    e.SetRemoved(true);
                    Score.score += 10;
                    destroyed = true;
                    break;
                }
            }
        }
        else
        {
            if (x - radius < player.GetX() + player.GetWidth() && x + radius > player.GetX() && y - radius < player.GetY() + player.GetHeight() && y + radius > player.GetY())
            {
                SpaceInvadersView.isDead = true;
            }
        }
    }
}
