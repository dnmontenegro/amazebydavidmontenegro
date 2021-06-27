package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class AMazeActivity extends AppCompatActivity {

    private TextView title;
    private TextView tSize;
    private SeekBar size;
    private TextView tProgress;
    private int mazeSize;
    private TextView tRooms;
    private Button rooms;
    private TextView tAlgorithm;
    private Spinner algorithm;
    private TextView tMode;
    private Spinner mode;
    private Button revisit;
    private Button explore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);

        title = (TextView) findViewById(R.id.title);
        tSize = (TextView) findViewById(R.id.tSize);
        size = (SeekBar) findViewById(R.id.size);
        tProgress = (TextView) findViewById(R.id.tProgress);
        tRooms = (TextView) findViewById(R.id.tRooms);
        tAlgorithm = (TextView) findViewById(R.id.tAlgorithm);
        tMode = (TextView) findViewById(R.id.tMode);

        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(15);
                tProgress.setText(getString(R.string.size, progress));
                mazeSize = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v((String) getApplicationContext().getText(R.string.tSize), (String) getApplicationContext().getText(R.string.inputDetected));
            }
        });

    }
}