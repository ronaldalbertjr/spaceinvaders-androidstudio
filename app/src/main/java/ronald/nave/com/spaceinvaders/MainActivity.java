package ronald.nave.com.spaceinvaders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ronald.nave.com.spaceinvaders.SpaceInvaders.SpaceInvaders;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent i = new Intent(this, SpaceInvaders.class);
        startActivity(i);
    }
}
