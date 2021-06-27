package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class AMazeActivity extends AppCompatActivity {

    private TextView title;
    private TextView tSize;
    private SeekBar size;
    private TextView tProgress;
    private int mazeSize;
    private TextView tRooms;
    private ToggleButton rooms;
    private boolean mazeRooms;
    private TextView tAlgorithm;
    private Spinner algorithm;
    private String mazeAlgorithm;
    private TextView tMode;
    private Spinner mode;
    private String mazeMode;
    private Button revisit;
    private Button explore;

    /**
     * This method runs upon the creation of the activity. The method's intended purpose
     * is to show the welcome page where the user can select parameter settings for the maze
     * generation. The user can choose to explore a new maze or revisit an old one.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);

        title = (TextView) findViewById(R.id.title);
        tSize = (TextView) findViewById(R.id.tSize);
        size = (SeekBar) findViewById(R.id.size);
        tProgress = (TextView) findViewById(R.id.tProgress);
        mazeSize = 0;
        tRooms = (TextView) findViewById(R.id.tRooms);
        rooms = (ToggleButton) findViewById(R.id.rooms);
        mazeRooms = true;
        tAlgorithm = (TextView) findViewById(R.id.tAlgorithm);
        algorithm = (Spinner) findViewById(R.id.algorithm);
        mazeAlgorithm = getString(R.string.DFS);
        tMode = (TextView) findViewById(R.id.tMode);
        mode = (Spinner) findViewById(R.id.mode);
        mazeMode = getString(R.string.Manual);
        revisit = (Button) findViewById(R.id.revisit);
        explore = (Button) findViewById(R.id.explore);

        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * This method updates the setting displayed on the screen by the user moving
             * the seekbar. It also updates the stored parameter for the maze size.
             * @param seekBar
             * @param progress
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(15);
                tProgress.setText(getString(R.string.size, progress));
                mazeSize = progress;
            }

            /**
             * This method runs upon the start of the seekbar being moved.
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * This method runs upon the end of the seekbar being moved.
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.tSize), Integer.toString(mazeSize));
            }
        });

        rooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * This method checks if the button to determine the rooms parameter has been
             * toggled. It also updates the stored parameter for rooms in the maze.
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mazeRooms = true;
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.tRooms), Boolean.toString(mazeRooms));
                }
                else {
                    mazeRooms = false;
                    Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                    Log.v(getString(R.string.tRooms), Boolean.toString(mazeRooms));
                }
            }
        });

        String [] algorithms = {getString(R.string.DFS), getString(R.string.Prim), getString(R.string.Kruskal)};
        ArrayAdapter algor = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, algorithms);
        algor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        algorithm.setAdapter(algor);
        algorithm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * This method checks if an item has been selected in the spinner. It also updates
             * the stored parameter for the maze algorithm.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mazeAlgorithm = algorithm.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.tAlgorithm), mazeAlgorithm);
            }

            /**
             * This method runs upon no item being selected in the spinner.
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String [] modes = {getString(R.string.Manual), getString(R.string.WallFollower), getString(R.string.Wizard)};
        ArrayAdapter mod = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, modes);
        mod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mode.setAdapter(mod);
        mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * This method checks if an item has been selected in the spinner. It also updates
             * the stored parameter for the maze mode.
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mazeMode = mode.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.tMode), mazeMode);
            }
            /**
             * This method runs upon no item being selected in the spinner.
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        revisit.setOnClickListener(new View.OnClickListener() {
            /**
             * This method checks if the button has been clicked. The method's intended purpose
             * is to send the user to a previously visited maze.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GeneratingActivity.class);
                i.putExtra("MazeSize", mazeSize);
                i.putExtra("MazeRooms", mazeRooms);
                i.putExtra("MazeAlgorithm", mazeAlgorithm);
                i.putExtra("MazeMode", mazeMode);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.revisit), getString(R.string.inputDetected));
                startActivity(i);
            }
        });

        explore.setOnClickListener(new View.OnClickListener() {
            /**
             * This method checks if the button has been clicked. The method's intended purpose
             * is to send the user to a new maze with the stored parameters.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GeneratingActivity.class);
                i.putExtra("MazeSize", mazeSize);
                i.putExtra("MazeRooms", mazeRooms);
                i.putExtra("MazeAlgorithm", mazeAlgorithm);
                i.putExtra("MazeMode", mazeMode);
                Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
                Log.v(getString(R.string.explore), getString(R.string.inputDetected));
                startActivity(i);
            }
        });







    }
}