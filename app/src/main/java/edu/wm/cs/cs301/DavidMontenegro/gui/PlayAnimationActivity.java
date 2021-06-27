package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class PlayAnimationActivity extends AppCompatActivity {

    private TextView mazeAnimation;
    private Button wholeMaze;
    private Button solution;
    private Button visibleWalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_animation);

        mazeAnimation = (TextView) findViewById(R.id.mazeAnimation);
        wholeMaze = (Button) findViewById(R.id.wholeMaze);
        solution = (Button) findViewById(R.id.solution);
        visibleWalls = (Button) findViewById(R.id.visibleWalls);
    }
}