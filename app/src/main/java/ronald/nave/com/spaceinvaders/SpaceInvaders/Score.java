package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Space;

/**
 * Created by ronald.junior on 11/04/2017.
 */

public class Score
{
    public static int score;
    private Paint white;

    public Score()
    {
        score = 0;

        white = new Paint();
        white.setARGB(255,255,255,255);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawText(String.valueOf(score), SpaceInvadersView.screenW * 0.02f, SpaceInvadersView.screenH * 0.03f, white);
    }
}
