package com.example.hackaton;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleActivity extends Activity {
    Display display;
    Random rnd = new Random();
    private int count;
    float speed = 0.002f;
    List<ImageView> listObj = new ArrayList<ImageView>();
    List<Boolean> listState = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        display = getWindowManager().getDefaultDisplay();

        new CountDownTimer(60000, 30) {

            public void onTick(long millisUntilFinished) {
                for (int i = 0; i < listObj.size(); i++) {
                    ImageView view = listObj.get(i);
                    int index = listObj.indexOf(view);

                    if (view.getScaleX() > 0.4) {
                        if (listState.get(index)) {
                            //EXIT
                        }
                        view.setScaleX(0);
                        view.setScaleY(0);
                        listState.remove(index);
                        listObj.remove(index);
                    }

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
        clone.setScaleX(0);
        clone.setScaleY(0);
        clone.setImageResource(R.drawable.circle);
        clone.setClickable(true);

        if (rnd.nextDouble() >= 0.75) {
            clone.setColorFilter(Color.RED);
            listState.add(true);
        } else {
            clone.setColorFilter(Color.GREEN);
            listState.add(false);
        }

        float maxX = display.getWidth() - 200;
        float minX = 100;
        clone.setX(rnd.nextFloat() * (maxX - minX) + minX);

        float maxY = display.getHeight() - 200;
        float minY = 100;
        clone.setY(rnd.nextFloat() * (maxY - minY) + minY);

        clone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = listObj.indexOf(clone);

                if (index == -1) return;

                if (listState.get(index)){
                    //EXIT
                } else {
                    clone.setScaleX(0);
                    clone.setScaleY(0);
                    listState.remove(index);
                    listObj.remove(index);
                    count++;
                    ((TextView)findViewById(R.id.counter)).setText(String.valueOf(count));
                }

            }
        });

        listObj.add(clone);
        addContentView(clone, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return clone;
    }
}
