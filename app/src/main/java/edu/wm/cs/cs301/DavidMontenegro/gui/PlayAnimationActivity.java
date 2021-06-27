package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class PlayAnimationActivity extends AppCompatActivity {

    private Button wholeMaze;
    private Button solution;
    private Button visibleWalls;
    private Button shortcut;
    private Button start;
    private int energy;
    private int path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_animation);

        wholeMaze = (Button) findViewById(R.id.wholeMaze);
        solution = (Button) findViewById(R.id.solution);
        visibleWalls = (Button) findViewById(R.id.visibleWalls);
        shortcut = (Button) findViewById(R.id.shortcut);
        start = (Button) findViewById(R.id.start);
        energy = 0;
        path = 0;

        shortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                i.putExtra("EnergyConsumption", energy);
                i.putExtra("PathLength", path);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.shortcut), getString(R.string.inputDetected));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
        Log.v(getString(R.string.back), getString(R.string.inputDetected));
        startActivity(i);
    }
}