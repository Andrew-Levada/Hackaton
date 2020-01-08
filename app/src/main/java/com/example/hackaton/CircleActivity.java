package com.example.hackaton;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleActivity extends Activity {
    Display display;
    Random rnd = new Random();
    private int count;
    float speed = 0.002f;
    List<ImageView> listObj = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = getWindowManager().getDefaultDisplay();

        final EditText result = (EditText)findViewById(R.id.answer);

        new CountDownTimer(60000, 30) {

            public void onTick(long millisUntilFinished) {
                for (ImageView view: listObj) {
                    view.setScaleX(view.getScaleX() + speed);
                    view.setScaleY(view.getScaleY() + speed);
                }
            }

            public void onFinish() {

            }

        }.start();

        new CountDownTimer(50000, 2000) {

            public void onTick(long millisUntilFinished) {
                createCircle();
            }

            public void onFinish() {
                System.out.println("DONE!");
            }

        }.start();
    }

    private ImageView createCircle() {
        final ImageView clone = new ImageView(getApplicationContext());
        clone.setId(count);
        clone.setScaleX(0);
        clone.setScaleY(0);
        clone.setImageResource(R.drawable.circle);
        clone.setClickable(true);

        if (rnd.nextDouble() >= 0.75)
            clone.setColorFilter(Color.RED);
        else
            clone.setColorFilter(Color.GREEN);

        float maxX = display.getWidth() - 200;
        float minX = 100;
        clone.setX(rnd.nextFloat() * (maxX - minX) + minX);

        float maxY = display.getHeight() - 200;
        float minY = 100;
        clone.setY(rnd.nextFloat() * (maxY - minY) + minY);

        clone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clone.getColorFilter() == new ColorFilter()){

                }

            }
        });

        listObj.add(clone);
        count++;
        addContentView(clone, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return clone;
    }
}
