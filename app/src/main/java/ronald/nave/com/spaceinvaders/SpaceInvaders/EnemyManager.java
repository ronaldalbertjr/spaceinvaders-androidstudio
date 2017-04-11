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

    private EnemyManager()
    {
        lines = 4;
        columns = 6;

        enemies = new ArrayList<>();
        SetupEnemies();
    }

    public static EnemyManager getInstance()
    {
        if(instance == null) instance = new EnemyManager();

        return instance;
    }
    public void SetupEnemies()
    {
        enemies.clear();

        for(int i = 0; i < lines; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                Enemy enemy = new Enemy();
                enemies.add(enemy);
            }
        }
    }

}
