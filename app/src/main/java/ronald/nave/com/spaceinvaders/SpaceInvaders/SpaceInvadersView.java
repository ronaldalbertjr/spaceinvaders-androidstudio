package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ronald.junior on 11/04/2017.
 */

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
    private Bullet bullet;
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

        bullet = new Bullet();
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
            bullet.draw(canvas);
            player.draw(canvas);
            score.draw(canvas);

            for(Enemy e : em.enemies) e.draw(canvas);
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
            bullet.update();
            player.update();
        }
    }
    private void RestartGame()
    {
        bullet = new Bullet();
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
