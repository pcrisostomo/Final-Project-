package com.mlabs.bbm.iquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class registeractivity extends AppCompatActivity {
    private loginactivity loginactivity;
    private Toast popToast;

    private DataBaseAdapter DatabaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_acc);

        final EditText[] email = {(EditText) findViewById(R.id.email)};
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText uname = (EditText) findViewById(R.id.uname);
        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText lastname = (EditText) findViewById(R.id.lastname);
        final EditText confirmpass = (EditText) findViewById(R.id.confirmpass);

        final Button btn_reg = (Button) findViewById(R.id.btn_reg);

        final Toast[] popToast = new Toast[1];

        DatabaseAdapter = new DataBaseAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();

        assert btn_reg != null;
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String frname = firstname.getText().toString();
                final String lname = lastname.getText().toString();
                final String passwordInput = password.getText().toString();
                final String passwordInputVerify = confirmpass.getText().toString();

                String emailadd = email[0].getText().toString();
                String username = uname.getText().toString();

                String pass1 = DatabaseAdapter.getEmailforsignup(emailadd);
                String pass2 = DatabaseAdapter.getUsernameforsignup(username);

                if (frname.equals("")||lname.equals("")||username.equals("")||emailadd.equals("")||passwordInput.equals("")||passwordInputVerify.equals("")){
                    Toast.makeText(getApplicationContext(), "No Empty Fields.", Toast.LENGTH_LONG).show();
                    return;
                } else if (!(Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)?").matcher(emailadd).matches())) {
                    Toast.makeText(registeractivity.this, "Invalid Email Address.", Toast.LENGTH_LONG).show();
                } else if (!(Pattern.compile("([a-zA-Z0-9]+)").matcher(password.getText()).matches() && password.getText().length() >= 8)) {
                    Toast.makeText(registeractivity.this, "Password length needs to be at least 8 characters.", Toast.LENGTH_LONG).show();
                } else if (!passwordInput.equals(passwordInputVerify)) {
                    Toast.makeText(registeractivity.this, "Password does not match.", Toast.LENGTH_LONG).show();
                    password.requestFocus();
                } else if (!(Pattern.compile("^([A-Za-z]*)+$").matcher(frname).matches())) {
                    Toast.makeText(registeractivity.this, "Invalid Firstname.", Toast.LENGTH_LONG).show();
                } else if (!(Pattern.compile("^([A-Za-z]*)+$").matcher(lname).matches())) {
                    Toast.makeText(registeractivity.this, "Invalid Lastname.", Toast.LENGTH_LONG).show();
                } /**else if (username.equals(pass2) | emailadd.equals(pass1)) {
                    Toast.makeText(registeractivity.this, "Username or Email already exists.", Toast.LENGTH_LONG).show();
                } **/
                else if (username.equals(pass2)){
                    Toast.makeText(registeractivity.this, "Username already exists.", Toast.LENGTH_LONG).show();
                }
                else if (emailadd.equals(pass1)){
                    Toast.makeText(registeractivity.this, "Email Address already exists.", Toast.LENGTH_LONG).show();
                }
                else  {
                    DataBaseAdapter.insertEntry(frname, lname, username, emailadd,passwordInput);
                    popToast[0] = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast[0].setText("Account Successfully Created!");
                    popToast[0].show();
                    Intent intent = new Intent(registeractivity.this, loginactivity.class);
                    startActivity(intent);

/**
                Log.d(getApplicationContext().toString(), "CLICK");
                if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)?").matcher(email[0].getText()).matches() && (Pattern.compile("([a-zA-Z0-9]+)").matcher(password.getText()).matches()) && password.getText().length() >= 8) {
                    Log.d(getApplicationContext().toString(), "PAU");
                    if (password.getText().toString().equals(confirmpass.getText().toString())) {
                        Log.d(registeractivity.this.toString(), "Signing Up..");
                        sqlDB.registerUser( email[0].getText().toString().trim(),password.getText().toString().trim(), firstname.getText().toString().trim(), lastname.getText().toString().trim(), uname.getText().toString().trim(), getDate());
                        Log.d(registeractivity.class.getSimpleName(), email[0].getText().toString().trim());
                        Toast.makeText(getApplicationContext(), "User successfully added", Toast.LENGTH_LONG).show();
                        Intent goBackToLoginScreen = new Intent(registeractivity.this, loginactivity.class);
                        startActivity(goBackToLoginScreen);
                    } else {
//hi
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                }
**/
            }
        }
    });
            }
    protected void onPause() {
        super.onPause();
        finish();
    }
    public String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    }


