package com.mlabs.bbm.iquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);

        Button btn_ontouch;

        btn_ontouch = (Button) findViewById(R.id.btn_ontouch);

        if (btn_ontouch != null) {
            btn_ontouch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                    Intent intent = new Intent(MainActivity.this, On_Touch.class);
                    startActivity(intent);
                }
            });
        }
    }
}
