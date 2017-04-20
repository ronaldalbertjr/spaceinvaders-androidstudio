package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Bullet
{
    private Paint red;
    private float x, y, radius, speedY;
    private boolean couldCollide;
    private Player player;
    private EnemyManager em;
    public boolean destroyed;

    public Bullet(float xPos, float yPos)
    {
        red = new Paint();
        red.setARGB(255, 255, 0, 0);

        x = xPos;
        y = yPos;
        radius = SpaceInvadersView.screenW * 0.01f;
        speedY = -6f;
        couldCollide = true;
        destroyed = false;

        player = Player.getInstance();
        em = EnemyManager.getInstance();
    }

    public void draw(Canvas canvas)
    {

        if(!destroyed) canvas.drawCircle(x , y, radius, red);
    }

    public void update()
    {
        y += speedY;

        CollisionWithScreen();
        CollisionWithEnemies();
    }

    private void CollisionWithScreen()
    {
        if(y + radius > SpaceInvadersView.screenH) destroyed = true;
        else couldCollide = true;
    }

    private void CollisionWithEnemies()
    {
        for(Enemy e:em.enemies)
        {
            if(!e.GetRemoved() && x - radius < e.GetX() + e.GetWidth() && x + radius > e.GetX() && y - radius < e.GetY() + e.GetHeight() && y + radius > e.GetY())
            {
                speedY *= -1;
                e.SetRemoved(true);
                Score.score += 10;
                destroyed = true;
                break;
            }
        }
    }

}
