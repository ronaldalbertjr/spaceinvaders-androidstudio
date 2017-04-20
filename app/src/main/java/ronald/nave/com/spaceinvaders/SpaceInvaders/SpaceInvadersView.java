package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class SpaceInvadersView extends View implements Runnable
{
    public static int screenW, screenH;
    public static boolean isDead, isPaused, isUpdating;

    private Handler handler;
    private Context c;

    private Paint white;
    private Player player;
    private EnemyManager em;
    private Score score;
    private List<Bullet> bullet;
    private float timer;
    private int HighScore;

    public SpaceInvadersView(Context ctx)
    {
        super(ctx);
        Start(ctx);
    }

    protected void Start(Context ctx)
    {
        c = ctx;
        setBackgroundColor(Color.BLACK);

        screenW = c.getResources().getDisplayMetrics().widthPixels;
        screenH = c.getResources().getDisplayMetrics().heightPixels;

        isDead = isPaused = false;
        isUpdating = true;

        white = new Paint();
        white.setARGB(255, 255, 255, 255);

        bullet = new ArrayList<Bullet>();
        player = Player.getInstance();
        em = EnemyManager.getInstance();
        score = new Score();

        handler = new Handler();
        handler.post(this);
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        if(!isDead && !isPaused) player.preUpdate(event);
        else if(isDead) RestartGame();
        else if(isPaused) isPaused = false;

        return true;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(!isDead && !isPaused)
        {
            player.draw(canvas);
            score.draw(canvas);

            for(Enemy e : em.enemies) e.draw(canvas);
            for(Bullet b : bullet) b.draw(canvas);
            for(Bullet b: em.enemyBullet) b.draw(canvas);
        }
        else
        {
            canvas.drawText("Clique para reiniciar", screenW * 0.2f, screenH * 0.5f, white);
        }
    }
    private void Update()
    {
        if(!isDead && !isPaused)
        {

            timer += 0.03;
            if(timer >= 3)
            {
                bullet.add(new Bullet(player.GetX() + (player.GetWidth()/2), player.GetY()+ (player.GetHeight()/2), true));
                timer = 0;
            }
            for(int i = 0; i < bullet.size(); i++)
            {
                if(!bullet.get(i).destroyed) bullet.get(i).update();
                else bullet.remove(bullet.get(i));
            }
            em.update(player);
            player.update();
        }
    }
    private void RestartGame()
    {
        score = new Score();
        em.SetupEnemies();
        isDead = false;
    }
    public void run()
    {
        if(isUpdating)
        {
            handler.postDelayed(this, 30);

            Update();
            invalidate();
        }
        else em.SetupEnemies();
    }
}
