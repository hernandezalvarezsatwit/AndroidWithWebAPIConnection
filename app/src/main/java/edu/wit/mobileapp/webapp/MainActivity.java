package edu.wit.mobileapp.webapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView activities;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activities = findViewById(R.id.activity_here);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        showMeAnActivity();
        //Button button = findViewById(R.id.button);
        //button.setOnClickListener(v -> showMeAnActivity(v));
    }

    private void showMeAnActivity() {
        progressBar.setVisibility(View.VISIBLE);
        FetchAnActivity faa = new FetchAnActivity(activities, progressBar);
        faa.execute();
    }
}