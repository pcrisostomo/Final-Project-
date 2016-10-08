package com.mlabs.bbm.iquiz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * Created by Pau on 10/8/2016.
 */
public class On_Touch extends AppCompatActivity{
        float x1, y1 , x2, y2, a, b;
        String msg1 = "", msg2="";
        ImageView imageLogo;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.on_touch);
            final EditText txy = (EditText) findViewById(R.id.editText3);
            final EditText txy2 = (EditText) findViewById(R.id.editText4);
            final EditText tdiff = (EditText) findViewById(R.id.editText5);
            final EditText tquadrant = (EditText) findViewById(R.id.editText6);
            final EditText tmotion = (EditText) findViewById(R.id.editText7);
            txy.setKeyListener(null);
            txy2.setKeyListener(null);
            tdiff.setKeyListener(null);
            tquadrant.setKeyListener(null);
            tmotion.setKeyListener(null);

            imageLogo = (ImageView) findViewById(R.id.imageView2);
            imageLogo.setOnTouchListener(new View.OnTouchListener() {
                float x,y,x1,y1;

                @Override
                public boolean onTouch(View view, MotionEvent e) {

                    String actionX = "";
                    String actionY = "";
                    String quadrant = "";

                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x = e.getX();
                            y = e.getY();
                            return true;
                        case MotionEvent.ACTION_UP:
                            float X = imageLogo.getRight()/2;
                            float Y = imageLogo.getBottom()/2;

                            x1=e.getX();
                            y1=e.getY();

                            actionX = "";
                            actionY = "";
                            quadrant = "";

                            if (x<x1){
                            if (x>x1){
                                actionX = "Swiped right ";
                            }
                                actionX = "Swiped left. ";
                            }
                            if (y<y1){
                                actionY = "Swiped down. ";
                            }
                            if (y>y1)
                            {
                                actionY = "Swiped up. ";
                            }

                            if(x1>X && y1>Y){
                                quadrant = "Quadrant 4";
                            }
                            if(x1<X && y1>Y){
                                quadrant = "Quadrant 3";
                            }
                            if(x1<X && y1<Y){
                                quadrant = "Quadrant 2";
                            }
                            if(x1>X && y1<Y){
                                quadrant = "Quadrant 1";
                            }

                            txy.setText(x + ", " + y);
                            txy2.setText(x1 + ", " + y1);
                            tdiff.setText(  (Math.abs(x1-x))+", "+ (Math.abs(y1-y)) );

                            tmotion.setText(actionX + actionY);
                            tquadrant.setText(quadrant);

                    }
                    return  false;
                }


            });
        }

    }

