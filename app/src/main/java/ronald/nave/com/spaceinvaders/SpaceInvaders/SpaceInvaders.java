package ronald.nave.com.spaceinvaders.SpaceInvaders;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class SpaceInvaders extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTitle("Space Invaders");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(new SpaceInvadersView(this));
    }

    protected void onPause()
    {
        super.onPause();
        SpaceInvadersView.isPaused = true;
        Toast.makeText(this, "Jogo Pausado", Toast.LENGTH_SHORT).show();
    }

    protected void onStop()
    {
        super.onStop();
        SpaceInvadersView.isPaused = true;
        Toast.makeText(this, "Jogo Parado", Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        SpaceInvadersView.isUpdating = false;
        Toast.makeText(this, "Jogo Destruído", Toast.LENGTH_SHORT).show();
    }
}
