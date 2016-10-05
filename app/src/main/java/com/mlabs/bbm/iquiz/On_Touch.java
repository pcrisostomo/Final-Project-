package com.mlabs.bbm.iquiz;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;


/**
 * Created by androidstudio on 10/09/16.
 */
public class On_Touch extends AppCompatActivity {
    float x1, y1 , x2, y2, a, b;
    String msg1 = "", msg2="";
    ImageView imageLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_touch);
        final EditText t1 = (EditText) findViewById(R.id.editText3);
        final EditText t2 = (EditText) findViewById(R.id.editText4);
        final EditText t3 = (EditText) findViewById(R.id.editText5);
        final EditText t4 = (EditText) findViewById(R.id.editText7);
        final EditText t5 = (EditText) findViewById(R.id.editText6);
        t1.setKeyListener(null);
        t2.setKeyListener(null);
        t3.setKeyListener(null);
        t4.setKeyListener(null);
        t5.setKeyListener(null);
        imageLogo = (ImageView) findViewById(R.id.imageView2);
        imageLogo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        t1.setText(x1 + ","+y1);
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2= event.getY();
                        t2.setText(x2 +","+y2);
                        a=x1-x2;
                        b=y1-y2;
                        t3.setText(Math.abs(a) +","+Math.abs(b));
                        if (a>0 & b>0 ){
                            msg2="2nd Quadrant";
                        }
                        if (a>0 & b<0){

                            msg2="3rd Quadrant";
                        }
                        if (a<0 & b<0){
                            msg2="4th Quadrant";
                        }
                        if (a<0 & b>0){

                            msg2="1st Quadrant";
                        }
                        if (y1 < y2 ) {
                            msg1 +=" Swiped Bottom";
                        }
                        if(y1 > y2 ){
                            msg1 +=" Swiped Up";
                        }
                        if (x1 > x2 ){
                            msg1 +=" Swiped Left";
                        }
                        if (x1 < x2 ) {

                            msg1 +=" Swiped Right";
                        }
                        t4.setText(msg1);
                        msg1="";
                        t5.setText(msg2);
                        msg2="";
                }
                return true;
            }
        });

    }




}





