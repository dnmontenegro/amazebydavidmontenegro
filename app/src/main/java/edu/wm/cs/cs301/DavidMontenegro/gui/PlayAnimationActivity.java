package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import edu.wm.cs.cs301.DavidMontenegro.R;
import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.Order;

public class PlayAnimationActivity extends AppCompatActivity {

    private ToggleButton wholeMazeAni;
    private ToggleButton solutionAni;
    private ToggleButton visibleWallsAni;
    //private Button shortcutAni;
    private MazePanel mazePanelAni;
    private TextView remainingEnergy;
    private ProgressBar energyProgress;
    private ToggleButton start;
    private int path;
    private float energy;
    private boolean winOrLose;
    private String mazeMode;
    private Maze maze;
    private StatePlaying statePlaying;
    private Thread driving;
    private BasicRobot basicRobot;
    private RobotDriver robotDriver;

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
        //shortcutAni= (Button) findViewById(R.id.shortcutAni);
        mazePanelAni = (MazePanel) findViewById(R.id.mazePanelAni);
        remainingEnergy = (TextView) findViewById(R.id.remainingEnergy);
        energyProgress = (ProgressBar) findViewById(R.id.energyProgress);
        start = (ToggleButton) findViewById(R.id.start);
        path = 0;
        energy = 0;
        winOrLose = false;
        energyProgress.setMax(2000);
        energyProgress.setProgress(2000);
        mazeMode = getIntent().getStringExtra("MazeMode");
        maze = GeneratingActivity.getMaze();
        assert maze != null : "maze must be present";
        statePlaying = new StatePlaying();
        statePlaying.setMazeConfiguration(maze);
        statePlaying.start(mazePanelAni);
        basicRobot = new BasicRobot();
        basicRobot.setState(statePlaying);
        switch (mazeMode) {
            case "Wall Follower":
                robotDriver = new WallFollower();
                break;
            case "Wizard":
                robotDriver = new Wizard();
                break;
        }
        robotDriver.setRobot(basicRobot);
        robotDriver.setMaze(maze);
        BasicSensor sensorForward = new BasicSensor();
        basicRobot.addDistanceSensor(sensorForward, Robot.Direction.FORWARD);
        BasicSensor sensorLeft = new BasicSensor();
        basicRobot.addDistanceSensor(sensorLeft, Robot.Direction.LEFT);

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
                    wholeMazeAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLEFULLMAP, 0);
                }
                else {
                    wholeMazeAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLEFULLMAP, 0);
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
                    solutionAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLESOLUTION, 0);
                }
                else {
                    solutionAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLESOLUTION, 0);
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
                    visibleWallsAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLELOCALMAP, 0);
                }
                else {
                    visibleWallsAni.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLELOCALMAP, 0);
                }
            }
        });

        /*shortcutAni.setOnClickListener(new View.OnClickListener() {
            *//**
             * This method advances the user to the finish activity. It is a placeholder for
             * the maze.
             * @param v
             *//*
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
        });*/

        start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * This method's intended purpose is to allow the user to start the exploration of the
             * maze by the robot and to pause te animation.
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    start.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.start), Boolean.toString(isChecked));
                    start.setEnabled(false);
                    driving.start();
                }
                else {
                    start.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.stop), Boolean.toString(isChecked));
                }
            }
        });
        driving = new Thread(new Runnable() {
            /**
             * This method's intended purpose is to mimic maze generation by incrementally
             * increasing the progress of the progressbar.
             */
            @Override
            public void run() {
                while(!basicRobot.isAtExit()) {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        return;
                    }
                    try {
                        robotDriver.drive1Step2Exit();
                        energyProgress.setProgress((int) basicRobot.getBatteryLevel());
                    } catch (Exception e) {
                        winOrLose = false;
                        Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                        i.putExtra("WinOrLose", winOrLose);
                        i.putExtra("PathLength", path);
                        i.putExtra("EnergyConsumption", energy);
                        startActivity(i);
                    }
                }
                try {
                    robotDriver.drive1Step2Exit();
                    energyProgress.setProgress((int) basicRobot.getBatteryLevel());
                } catch (Exception e) {
                    winOrLose = false;
                    Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                    i.putExtra("WinOrLose", winOrLose);
                    i.putExtra("PathLength", path);
                    i.putExtra("EnergyConsumption", energy);
                    startActivity(i);
                }
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    return;
                }
                path = robotDriver.getPathLength();
                energy = robotDriver.getEnergyConsumption();
                energyProgress.setProgress((int) basicRobot.getBatteryLevel());
                if(basicRobot.canSeeThroughTheExitIntoEternity(Robot.Direction.FORWARD)) {
                    winOrLose = true;
                    Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                    i.putExtra("WinOrLose", winOrLose);
                    i.putExtra("PathLength", path);
                    i.putExtra("EnergyConsumption", energy);
                    startActivity(i);
                }
            }
        });
    }
    /**
     * This method returns the user to the welcome page when the back button is pressed.
     */
    @Override
    public void onBackPressed(){
        driving.interrupt();
        Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
        Log.v(getString(R.string.back), getString(R.string.inputDetected));
        startActivity(i);
    }
}