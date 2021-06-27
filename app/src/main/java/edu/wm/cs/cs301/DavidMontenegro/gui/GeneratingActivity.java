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

public class GeneratingActivity extends AppCompatActivity {

    private TextView genProgress;
    private ProgressBar mazeProgress;
    private TextView percentProgress;
    private Button genBack;
    private int mazeSize;
    private boolean mazeRooms;
    private String mazeAlgorithm;
    private String mazeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        genProgress = (TextView) findViewById(R.id.genProgress);
        mazeProgress = (ProgressBar) findViewById(R.id.mazeProgress);
        percentProgress = (TextView) findViewById(R.id.percentProgress);
        genBack = (Button) findViewById(R.id.genBack);
        mazeSize = getIntent().getIntExtra("MazeSize", 0);
        mazeRooms = getIntent().getBooleanExtra("MazeRooms", true);
        mazeAlgorithm = getIntent().getStringExtra("MazeAlgorithm");
        mazeMode = getIntent().getStringExtra("MazeMode");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mazeProgress.getProgress() < 100) {
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mazeProgress.incrementProgressBy(5);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            percentProgress.setText(getString(R.string.percent, mazeProgress.getProgress()));
                        }
                    });
                }
                Intent i;
                if (mazeMode.equals(getString(R.string.Manual)))
                    i = new Intent(getApplicationContext(), PlayManuallyActivity.class);
                else
                    i = new Intent(getApplicationContext(), PlayAnimationActivity.class);
                startActivity(i);
            }
        }).start();

        genBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.genBack), getString(R.string.inputDetected));
                startActivity(i);
            }
        });



    }
}