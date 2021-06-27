package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class PlayManuallyActivity extends AppCompatActivity {

    private ToggleButton wholeMazeMan;
    private ToggleButton  solutionMan;
    private ToggleButton  visibleWallsMan;
    private Button shortcutMan;
    private Button up;
    private Button left;
    private Button jump;
    private Button right;
    private Button down;
    private int energy;
    private int path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_manually);

        wholeMazeMan = (ToggleButton) findViewById(R.id.wholeMazeMan);
        solutionMan = (ToggleButton) findViewById(R.id.solutionMan);
        visibleWallsMan = (ToggleButton) findViewById(R.id.visibleWallsMan);
        shortcutMan = (Button) findViewById(R.id.shortcutMan);
        up = (Button) findViewById(R.id.up);
        left = (Button) findViewById(R.id.left);
        jump = (Button) findViewById(R.id.jump);
        right = (Button) findViewById(R.id.right);
        down = (Button) findViewById(R.id.down);
        energy = 0;
        path = 0;

        wholeMazeMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    wholeMazeMan.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                }
                else {
                    wholeMazeMan.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                }
            }
        });

        solutionMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    solutionMan.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                }
                else {
                    solutionMan.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                }
            }
        });

        visibleWallsMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    visibleWallsMan.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                }
                else {
                    visibleWallsMan.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                }
            }
        });

        shortcutMan.setOnClickListener(new View.OnClickListener() {
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

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.up), getString(R.string.inputDetected));
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.left), getString(R.string.inputDetected));
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.jump), getString(R.string.inputDetected));
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.right), getString(R.string.inputDetected));
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.down), getString(R.string.inputDetected));
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