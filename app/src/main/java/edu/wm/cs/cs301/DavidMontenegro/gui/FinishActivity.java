package edu.wm.cs.cs301.DavidMontenegro.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import edu.wm.cs.cs301.DavidMontenegro.R;

public class FinishActivity extends AppCompatActivity {

    private int path;
    private int energy;
    private boolean winOrLose;
    private TextView tPath;
    private TextView finPath;
    private TextView tEnergy;
    private TextView finEnergy;
    private TextView finWinOrLose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        path = getIntent().getIntExtra("PathLength", 0);
        energy = getIntent().getIntExtra("EnergyConsumption", 0);
        winOrLose = getIntent().getBooleanExtra("WinOrLose", true);
        tPath = (TextView) findViewById(R.id.tPath);
        finPath = (TextView) findViewById(R.id.finPath);
        finPath.setText(getString(R.string.finPath, path));
        tEnergy = (TextView) findViewById(R.id.tEnergy);
        finEnergy = (TextView) findViewById(R.id.finEnergy);
        finEnergy.setText(getString(R.string.finEnergy, energy));
        finWinOrLose = (TextView) findViewById(R.id.finWinOrLose);
        if(winOrLose)
            finWinOrLose.setText(getString(R.string.victory));
        else
            finWinOrLose.setText(getString(R.string.failure));

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), R.string.inputDetected, Toast.LENGTH_SHORT).show();
        Log.v(getString(R.string.back), getString(R.string.inputDetected));
        startActivity(i);
    }
}