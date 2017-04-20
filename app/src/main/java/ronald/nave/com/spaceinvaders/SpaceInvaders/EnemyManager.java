package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class EnemyManager
{
    private static EnemyManager instance;

    private int lines, columns;
    public List<Enemy> enemies;
    Enemy leftEnemy,rightEnemy, closestEnemy;
    List<Bullet> enemyBullet;
    float speedX, timer;

    private EnemyManager()
    {
        lines = 5;
        columns = 7;

        speedX = 2;
        enemies = new ArrayList<>();
        enemyBullet = new ArrayList<>();
        SetupEnemies();
    }

    public static EnemyManager getInstance()
    {
        if(instance == null) instance = new EnemyManager();

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
        if(timer >= 1)
        {
            timer = 0;
            enemyBullet.add(new Bullet(closestEnemy.GetX() + (closestEnemy.GetWidth()/2), closestEnemy.GetY() + (closestEnemy.GetHeight()/2), false));
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
                Enemy enemy = new Enemy(i, j, columns);
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
