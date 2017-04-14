package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Bullet
{
    private Paint red;
    private float x, y, radius, speedX, speedY;
    private boolean couldCollide;
    private Player player;
    private EnemyManager em;

    public Bullet()
    {
        red = new Paint();
        red.setARGB(255, 255, 0, 0);

        x = SpaceInvadersView.screenW / 2;
        y = SpaceInvadersView.screenH / 2;
        radius = SpaceInvadersView.screenW * 0.04f;
        speedX = 6f;
        speedY = -6f;
        couldCollide = true;

        player = Player.getInstance();
        em = EnemyManager.getInstance();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x , y, radius, red);
    }

    public void update()
    {
        x += speedX;
        y += speedY;

        CollisionWithScreen();
        CollisionWithEnemies();
        CollisionWithPlayer();
    }

    private void ChangeBallState(boolean width)
    {
        if(couldCollide)
        {
            if(width) speedX *= -1;
            else speedY *= -1;

            couldCollide = false;
        }
    }

    private void CollisionWithScreen()
    {
        if(x + radius > SpaceInvadersView.screenW || x - radius < 0) ChangeBallState(true);
        else if(y - radius < 0) ChangeBallState(false);
        else if(y + radius > SpaceInvadersView.screenH) SpaceInvadersView.isDead = true;
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
                break;
            }
        }
    }

    private void CollisionWithPlayer()
    {
        if(x - radius < player.GetX() + player.GetWidth() && x + radius > player.GetX() && y - radius < player.GetY() + player.GetHeight() && y + radius > player.GetY())
        {
            speedY *= -1;
            couldCollide = false;
        }
    }
}
