package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class AMazeActivity extends AppCompatActivity {

    private TextView title;
    private TextView tSize;
    private SeekBar size;
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
        tRooms = (TextView) findViewById(R.id.tRooms);
        tAlgorithm = (TextView) findViewById(R.id.tAlgorithm);
        tMode = (TextView) findViewById(R.id.tMode);

        title.setText(R.string.title);
        tSize.setText(R.string.tSize);
        tRooms.setText(R.string.tRooms);
        tAlgorithm.setText(R.string.tAlgorithm);
        tMode.setText(R.string.tMode);

    }
}