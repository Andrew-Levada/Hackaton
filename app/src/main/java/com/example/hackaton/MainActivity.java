package com.example.hackaton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Display display;
    Random rnd = new Random();
    private int count;
    float speed = 3;
    List<TextView> listTV = new ArrayList<TextView>();
    List<String> results = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = getWindowManager().getDefaultDisplay();

        final EditText result = (EditText)findViewById(R.id.answer);

        new CountDownTimer(60000, 30) {

            public void onTick(long millisUntilFinished) {
                for (TextView view: listTV) {
                    if (view.getY() >= display.getHeight())
                        System.out.println("STOP");

                    view.setY(view.getY() + speed);
                }

                int index = results.indexOf(result.getText().toString());
                if (index != -1) {
                    //((ViewGroup)findViewById(R.id.mainParent)).removeView(listTV.get(index));
                    listTV.get(index).setText("");
                    listTV.remove(index);
                    results.remove(index);
                    result.setText("");
                }
            }

            public void onFinish() {

            }

        }.start();

        new CountDownTimer(50000, 4500) {

            public void onTick(long millisUntilFinished) {
                createTextView();
                speed += 0.15;
            }

            public void onFinish() {
                System.out.println("DONE!");
            }

        }.start();
    }

    private TextView createTextView() {
        TextView clone = new TextView(getApplicationContext());
        clone.setId(count);
        clone.setTextSize(32);
        clone.setTextColor(Color.BLACK);
        clone.setY(0);

        float maxX = display.getWidth() - 150;
        float minX = 150;
        clone.setX(rnd.nextFloat() * (maxX - minX) + minX);

        int o = Content.getOperation();
        int a = Content.getA(o);
        int b = Content.getB(o);
        clone.setText(a + Content.getOperationString(o) + b);
        results.add(String.valueOf(Content.count(a, b, o)));

        listTV.add(clone);
        count++;
        addContentView(clone, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return clone;
    }
}
