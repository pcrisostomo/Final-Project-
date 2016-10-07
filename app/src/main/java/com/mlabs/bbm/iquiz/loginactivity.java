package com.mlabs.bbm.iquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pau on 7/21/2016.
 */
public class loginactivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlogin;
        final EditText EmailAdd, PassW;
        TextView txtsign;
        DataBaseAdapter DatabaseAdapter;

        EmailAdd = (EditText) findViewById(R.id.editText);
        PassW = (EditText) findViewById(R.id.editText2);
        btnlogin = (Button) findViewById(R.id.button2);
        Button btn1 = (Button) findViewById(R.id.tv_lbl1);
        txtsign = (TextView) findViewById(R.id.txtsignup);

        DatabaseAdapter = new DataBaseAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();

        assert btnlogin != null;
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailAdd.getText().toString();
                String pword = PassW.getText().toString();
                String uname = EmailAdd.getText().toString();
                String pass1 = DataBaseAdapter.getSinlgeEntry(email);
                String pass2 = DataBaseAdapter.getUsername(uname);

                if (pword.equals(pass2)|pword.equals(pass1)) {
                    Toast.makeText(loginactivity.this, "Login Successful.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(loginactivity.this, MainActivity.class);
                    startActivity(intent);
                    EmailAdd.setText("");
                    PassW.setText("");
                    finish();
                }
                else{
                    Toast.makeText(loginactivity.this, "Invalid Email or Password.", Toast.LENGTH_LONG).show();
                }
            }
            });
    /**
        if (btnlogin != null) {
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
                    boolean res = false;
                    res = db.validateUser(EmailAdd.getText().toString().trim(),PassW.getText().toString().trim());

                    if (res == true) {
                        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginactivity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
**/
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        PassW.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        PassW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

        txtsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, registeractivity.class);
                startActivity(intent);
            }
        });
    }
/**
    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }


    }
 **/

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }
}
