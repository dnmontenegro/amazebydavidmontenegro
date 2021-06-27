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

public class PlayAnimationActivity extends AppCompatActivity {

    private ToggleButton wholeMazeAni;
    private ToggleButton solutionAni;
    private ToggleButton visibleWallsAni;
    private Button shortcutAni;
    private TextView remainingEnergy;
    private ProgressBar energyProgress;
    private Button start;
    private int path;
    private int energy;
    private boolean winOrLose;

    /**
     * This method runs upon the creation of the activity. The method's intended purpose
     * is to display the maze and allow the user to watch a robot exploring the maze.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_animation);

        wholeMazeAni = (ToggleButton) findViewById(R.id.wholeMazeAni);
        solutionAni = (ToggleButton) findViewById(R.id.solutionAni);
        visibleWallsAni = (ToggleButton) findViewById(R.id.visibleWallsAni);
        shortcutAni= (Button) findViewById(R.id.shortcutAni);
        remainingEnergy = (TextView) findViewById(R.id.remainingEnergy);
        energyProgress = (ProgressBar) findViewById(R.id.energyProgress);
        start = (Button) findViewById(R.id.start);
        path = 0;
        energy = 0;
        winOrLose = false;
        energyProgress.setProgress(100);

        wholeMazeAni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * This method checks if the button has been toggled. It updates the color of the
             * button to show the user whether the button is toggled on or not. The method's
             * intended purpose is to show the whole maze from the top or not.
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    wholeMazeAni.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                }
                else {
                    wholeMazeAni.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                }
            }
        });

        solutionAni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * This method checks if the button has been toggled. It updates the color of the
             * button to show the user whether the button is toggled on or not. The method's
             * intended purpose is to show the solution in the maze or not.
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    solutionAni.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                }
                else {
                    solutionAni.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                }
            }
        });

        visibleWallsAni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * This method checks if the button has been toggled. It updates the color of the
             * button to show the user whether the button is toggled on or not. The method's
             * intended purpose is to show the currently visible walls or not.
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    visibleWallsAni.setBackgroundColor(getResources().getColor(R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                }
                else {
                    visibleWallsAni.setBackgroundColor(getResources().getColor(R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                }
            }
        });

        shortcutAni.setOnClickListener(new View.OnClickListener() {
            /**
             * This method advances the user to the finish activity. It is a placeholder for
             * the maze.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                i.putExtra("PathLength", path);
                i.putExtra("EnergyConsumption", energy);
                i.putExtra("WinOrLose", winOrLose);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.shortcut), getString(R.string.inputDetected));
                startActivity(i);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to start the exploration of the
             * maze by the robot and to pause te animation.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.start), getString(R.string.inputDetected));
            }
        });
    }
    /**
     * This method returns the user to the welcome page when the back button is pressed.
     */
    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
        Log.v(getString(R.string.back), getString(R.string.inputDetected));
        startActivity(i);
    }
}