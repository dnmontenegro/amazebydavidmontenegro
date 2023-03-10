package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
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
import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeContainer;

public class PlayManuallyActivity extends AppCompatActivity {

    private ToggleButton wholeMazeMan;
    private ToggleButton  solutionMan;
    private ToggleButton  visibleWallsMan;
    // private Button shortcutMan;
    private MazePanel mazePanel;
    private Button up;
    private Button left;
    private Button jump;
    private Button right;
    private Button down;
    private boolean winOrLose;
    private String mazeMode;
    private Maze maze;
    private StatePlaying statePlaying;
    private int path;

    /**
     * This method runs upon the creation of the activity. The method's intended purpose
     * is to display the maze and allow the user to manually navigate the maze.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_manually);

        wholeMazeMan = (ToggleButton) findViewById(R.id.wholeMazeMan);
        solutionMan = (ToggleButton) findViewById(R.id.solutionMan);
        visibleWallsMan = (ToggleButton) findViewById(R.id.visibleWallsMan);
        // shortcutMan = (Button) findViewById(R.id.shortcutMan);
        mazePanel = (MazePanel) findViewById(R.id.mazePanel);
        up = (Button) findViewById(R.id.up);
        left = (Button) findViewById(R.id.left);
        jump = (Button) findViewById(R.id.jump);
        right = (Button) findViewById(R.id.right);
        down = (Button) findViewById(R.id.down);
        winOrLose = true;
        mazeMode = getIntent().getStringExtra("MazeMode");
        maze = GeneratingActivity.getMaze();
        assert maze != null : "maze must be present";
        statePlaying = new StatePlaying();
        statePlaying.setMazeConfiguration(maze);
        statePlaying.start(mazePanel);
        path = 0;

        wholeMazeMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    wholeMazeMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLEFULLMAP, 0);
                }
                else {
                    wholeMazeMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.wholeMaze), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLEFULLMAP, 0);
                }
            }
        });

        solutionMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    solutionMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLESOLUTION, 0);
                }
                else {
                    solutionMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.solution), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLESOLUTION, 0);
                }
            }
        });

        visibleWallsMan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    visibleWallsMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLELOCALMAP, 0);
                }
                else {
                    visibleWallsMan.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.visible), Boolean.toString(isChecked));
                    statePlaying.keyDown(Constants.UserInput.TOGGLELOCALMAP, 0);
                }
            }
        });



        /*shortcutMan.setOnClickListener(new View.OnClickListener() {
            *//**
             * This method advances the user to the finish activity. It is a placeholder for
             * the maze.
             * @param v
             *//*
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                i.putExtra("WinOrLose", winOrLose);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.shortcut), getString(R.string.inputDetected));
                startActivity(i);
            }
        });*/

        up.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to nagivate up.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.up), getString(R.string.inputDetected));
                statePlaying.keyDown(Constants.UserInput.UP, 0);
                path++;
                if(!maze.isValidPosition(statePlaying.px,statePlaying.py)) {
                    winOrLose = true;
                    Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                    i.putExtra("WinOrLose", winOrLose);
                    i.putExtra("PathLength", path);
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.shortcut), getString(R.string.inputDetected));
                    startActivity(i);
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to nagivate left.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.left), getString(R.string.inputDetected));
                statePlaying.keyDown(Constants.UserInput.LEFT, 0);
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to jump.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.jump), getString(R.string.inputDetected));
                statePlaying.keyDown(Constants.UserInput.JUMP, 0);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to nagivate right.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.right), getString(R.string.inputDetected));
                statePlaying.keyDown(Constants.UserInput.RIGHT, 0);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            /**
             * This method's intended purpose is to allow the user to nagivate down.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.down), getString(R.string.inputDetected));
                statePlaying.keyDown(Constants.UserInput.DOWN, 0);
                path++;
                if(!maze.isValidPosition(statePlaying.px,statePlaying.py)) {
                    winOrLose = true;
                    Intent i = new Intent(getApplicationContext(), FinishActivity.class);
                    i.putExtra("WinOrLose", winOrLose);
                    i.putExtra("PathLength", path);
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.shortcut), getString(R.string.inputDetected));
                    startActivity(i);
                }
            }
        });

        /*mazePanel.setColor(Color.RED);
        mazePanel.addFilledRectangle(0, 0, 200, 200);
        mazePanel.setColor(Color.YELLOW);
        mazePanel.addFilledRectangle(200, 200, 200, 200);
        mazePanel.setColor(Color.BLUE);
        mazePanel.addFilledRectangle(0, 200, 200, 200);
        mazePanel.setColor(Color.GREEN);
        mazePanel.addFilledRectangle(200, 0, 200, 200);
        mazePanel.setColor(Color.WHITE);
        mazePanel.addLine(0,0, 400, 400);
        mazePanel.addLine(0,400, 400, 0);
        mazePanel.setColor(Color.BLACK);
        mazePanel.addFilledOval(0, 0, 200, 200);
        mazePanel.setColor(Color.CYAN);
        mazePanel.addFilledOval(200, 100, 200, 200);
        mazePanel.setColor(Color.MAGENTA);
        mazePanel.addArc(200, 100, 200, 200,0,90);
        int[] xPoints = {200, 300, 100};
        int[] yPoints = {0, 200, 200};
        mazePanel.setColor(Color.DKGRAY);
        mazePanel.addPolygon(xPoints, yPoints, 3);
        int[] xPoints2 = {200, 300, 100};
        int[] yPoints2 = {400, 200, 200};
        mazePanel.setColor(Color.LTGRAY);
        mazePanel.addFilledPolygon(xPoints2, yPoints2, 3);
        mazePanel.setColor(Color.BLUE);
        mazePanel.addMarker(100, 100, "Hello World!");
        mazePanel.addBackground(0.20F);
        mazePanel.commit();*/

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