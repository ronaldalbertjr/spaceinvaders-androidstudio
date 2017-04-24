package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ronald.nave.com.spaceinvaders.R;


public class EnemyManager
{
    private static EnemyManager instance;

    private int lines, columns;
    public List<Enemy> enemies;
    Enemy leftEnemy,rightEnemy, closestEnemy;
    float imgWidth, imgHeight;
    List<Bullet> enemyBullet;
    float speedX, timer;
    public Bitmap bm;
    Context c;

    private EnemyManager(Context ctx)
    {
        c = ctx;

        lines = 5;
        columns = 7;

        speedX = 2;
        imgWidth = (SpaceInvadersView.screenW / columns) - ((SpaceInvadersView.screenW * 0.05f) + 2 * columns) / columns;
        imgHeight = SpaceInvadersView.screenH * 0.05f;
        bm = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.space_invader);
        bm = Bitmap.createScaledBitmap(bm, (int) imgWidth, (int) imgHeight, false);
        enemies = new ArrayList<>();
        enemyBullet = new ArrayList<>();
        SetupEnemies();
    }

    public static EnemyManager getInstance(Context ctx)
    {
        if(instance == null) instance = new EnemyManager(ctx);

        return instance;
    }
    public void update(Player player)
    {
        GetClosestEnemyToPlayer(player);
        for(Enemy e: enemies)
        {
            e.update(speedX);
        }

        timer += 0.03;
        if(timer >= 5)
        {
            timer = 0;
            enemyBullet.add(new Bullet(closestEnemy.GetX() + (closestEnemy.GetWidth()/2), closestEnemy.GetY() + (closestEnemy.GetHeight()/2), false, c));
        }
        for(int i = 0; i < enemyBullet.size(); i++)
        {
            if(!enemyBullet.get(i).destroyed) enemyBullet.get(i).update();
            else enemyBullet.remove(enemyBullet.get(i));
        }
        CheckCollisionWithScreen();
    }
    private void CheckCollisionWithScreen()
    {
        if(leftEnemy.GetX() < 0)
        {
            speedX = 2;
        }
        else if(rightEnemy.GetX() + rightEnemy.GetWidth() > SpaceInvadersView.screenW)
        {
            speedX = -2;
        }
    }
    public void SetupEnemies()
    {
        enemies.clear();

        for(int i = 0; i < lines; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                Enemy enemy = new Enemy(i, j, columns, c);
                if(i == lines - 1 && j == 0)
                {
                    leftEnemy = enemy;
                }
                else if(i == lines - 1 && j == columns - 1)
                {
                    rightEnemy = enemy;
                }
                enemies.add(enemy);
            }
        }
    }

    private void GetClosestEnemyToPlayer(Player player)
    {
        float[] closestDistance = new float[2];
        closestDistance[0] = 9999999;
        closestDistance[1] = 9999999;
        for(Enemy e: enemies)
        {
            if(Math.abs(e.GetX() - player.GetX()) < closestDistance[0] && Math.abs(e.GetY() - player.GetY()) < closestDistance[1])
            {
                closestEnemy = e;
                closestDistance[0] = Math.abs(e.GetX() - player.GetX());
                closestDistance[1] = Math.abs(e.GetY() - player.GetY());
            }
        }
    }
}
