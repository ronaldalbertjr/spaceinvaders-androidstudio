package ronald.nave.com.spaceinvaders.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class EnemyManager
{
    private static EnemyManager instance;

    private int lines, columns;
    public List<Enemy> enemies;
    Enemy leftEnemy,rightEnemy;
    float speedX;

    private EnemyManager()
    {
        lines = 5;
        columns = 7;

        speedX = 2;
        enemies = new ArrayList<>();
        SetupEnemies();
    }

    public static EnemyManager getInstance()
    {
        if(instance == null) instance = new EnemyManager();

        return instance;
    }
    public void update()
    {
        for(Enemy e: enemies)
        {
            e.update(speedX);
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

}
