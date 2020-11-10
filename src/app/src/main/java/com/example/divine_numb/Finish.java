package com.example.divine_numb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle arguments = getIntent().getExtras();
        String s = arguments.get("steps").toString();
        String t = arguments.get("time").toString();

        TextView step = (TextView) findViewById(R.id.count_steps);
        TextView time = (TextView) findViewById(R.id.count_time);
        step.setText("Ходов: " + s);
        time.setText("Затрачено времени: " + t + " сек.");
    }

    public void Next_activity(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}