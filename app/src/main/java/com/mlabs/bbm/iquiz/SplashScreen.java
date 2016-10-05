package com.mlabs.bbm.iquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by benjarmanalili on 16/07/2016.
 */
public class SplashScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final ImageView iv = (ImageView)findViewById(R.id.imageView4);
        final Animation  an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);

        final ImageView iv2 = (ImageView)findViewById(R.id.imageView5);
        final Animation  an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate2);



        iv.startAnimation(an);
        iv2.startAnimation(an2);



    Thread timerThread = new Thread(){
        public void run(){
            try{
                sleep(3000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally{
                Intent intent = new Intent(SplashScreen.this,loginactivity.class );
                startActivity(intent);
            }
        }
    };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
