package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.wm.cs.cs301.DavidMontenegro.R;
import edu.wm.cs.cs301.DavidMontenegro.generation.Maze;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeContainer;
import edu.wm.cs.cs301.DavidMontenegro.generation.MazeFactory;
import edu.wm.cs.cs301.DavidMontenegro.generation.Order;

public class GeneratingActivity extends AppCompatActivity implements Order {

    private TextView genProgress;
    private ProgressBar mazeProgress;
    private TextView percentProgress;
    private int mazeSize;
    private boolean mazeRooms;
    private String mazeAlgorithm;
    private String mazeMode;
    private int randomSeed;
    private Thread generation;
    private Builder builder;
    private int percentdone;
    private MazeFactory mazeFactory;
    private static Maze maze;

    /**
     * This method runs upon the creation of the activity. The method's intended purpose
     * is to show an intermediate page where the user is shown the progress of the maze
     * generation.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        genProgress = (TextView) findViewById(R.id.genProgress);
        mazeProgress = (ProgressBar) findViewById(R.id.mazeProgress);
        percentProgress = (TextView) findViewById(R.id.percentProgress);
        mazeSize = getIntent().getIntExtra("MazeSize", 0);
        mazeRooms = getIntent().getBooleanExtra("MazeRooms", true);
        mazeAlgorithm = getIntent().getStringExtra("MazeAlgorithm");
        mazeMode = getIntent().getStringExtra("MazeMode");
        randomSeed = getIntent().getIntExtra("Seed", 0);
        percentdone = 0;

        switch (mazeAlgorithm) {
            case "DFS":
                builder = Builder.DFS;
                break;
            case "Prim":
                builder = Builder.Prim;
                break;
            case "Kruskal":
                builder = Builder.Kruskal;
                break;
        }

        mazeFactory = new MazeFactory();

        mazeFactory.order(this);

        generation = new Thread(new Runnable() {
            /**
             * This method's intended purpose is to mimic maze generation by incrementally
             * increasing the progress of the progressbar.
             */
            @Override
            public void run() {
                while(percentdone < 100) {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                        return;
                    }
                    mazeProgress.setProgress(percentdone);
                    runOnUiThread(new Runnable() {
                        /**
                         * This method updates the text under the progressbar to show the user
                         * the current progress.
                         */
                        @Override
                        public void run() {
                            percentProgress.setText(getString(R.string.percent, mazeProgress.getProgress()));
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    return;
                }
                Intent i;
                if (mazeMode.equals(getString(R.string.Manual)))
                    i = new Intent(getApplicationContext(), PlayManuallyActivity.class);
                else
                    i = new Intent(getApplicationContext(), PlayAnimationActivity.class);
                i.putExtra("MazeMode", mazeMode);
                startActivity(i);
            }
        });
        generation.start();
    }

    /**
     * This method returns the user to the welcome page when the back button is pressed.
     */
    @Override
    public void onBackPressed(){
        generation.interrupt();
        mazeFactory.cancel();
        Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
        Log.v(getString(R.string.back), getString(R.string.inputDetected));
        startActivity(i);
    }

    @Override
    public int getSkillLevel() {
        return mazeSize;
    }

    @Override
    public Builder getBuilder() {
        return builder;
    }

    @Override
    public boolean isPerfect() {
        return mazeRooms;
    }

    @Override
    public int getSeed() {
        return randomSeed;
    }

    @Override
    public void deliver(Maze mazeConfig) {
        setMaze(mazeConfig);
        Log.v(getString(R.string.maze), getString(R.string.maze));

    }

    @Override
    public void updateProgress(int percentage) {
        if (this.percentdone < percentage && percentage <= 100) {
            this.percentdone = percentage;
        }
    }

    public static Maze getMaze() {
        return maze;
    }

    public static void setMaze(Maze mazeConfig) {
        maze = mazeConfig;
    }
}