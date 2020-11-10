package com.example.divine_numb;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
private int steps;
private LocalDateTime start_game;
private int search_number;
private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        steps = 0;                                   //количество затраченных шагов
        start_game = java.time.LocalDateTime.now();  //фиксирование времени начала игры
        search_number = (int)(Math.random()*100);    //генерация числа
    }

    public boolean Length(EditText editText)        //проверка, введено ли значение в текстовое поле
    {
        boolean answer = true;
        if(editText.length()==0)
        {
            answer = false;
        }
        return answer;
    }

    public boolean Range(int number)               //проверка вхождения в диапазон
    {
        boolean answer = true;
        if(number < 1 || number > 100)
            answer = false;
        return answer;
    }

    public void Divine(View view)                 //обработка кнопки
    {
        EditText editText = (EditText) findViewById(R.id.input_text);
        TextView textView = (TextView) findViewById(R.id.tooltip);
        CountDownTimer timer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {
                textView.setVisibility(View.INVISIBLE);
                editText.setText("");
            }
        }.start();
        if(!Length(editText))
        {
            textView.setText("Введите для начала число");
        }
        else
        {
            int current = Integer.parseInt(editText.getText().toString());
            if(Range(current))
            {
                if(current > search_number)
                {
                    steps++;
                    textView.setText("Число больше заданного");
                }
                if(current < search_number)
                {
                    steps++;
                    textView.setText("Число меньше заданного");
                }
                if(current == search_number)
                {
                    Duration duration = Duration.between(start_game, java.time.LocalDateTime.now());
                    Intent intent = new Intent(this, Finish.class);
                    intent.putExtra("steps", steps);
                    intent.putExtra("time", "" + duration.toMillis()/1000);
                    startActivity(intent);
                }
            }
            else
                textView.setText("Число не в диапазоне");
        }




    }
}