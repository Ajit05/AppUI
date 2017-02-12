package com.ui.ajit.appui;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText user_email, user_password;
    private Button sign_in;
    private TextView createAccount;
    TraceBookAdapter vivzHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_email = (EditText) findViewById(R.id.user_mail_mob);
        user_password = (EditText) findViewById(R.id.user_password);
        sign_in = (Button) findViewById(R.id.user_signin);
        createAccount = (TextView) findViewById(R.id.signup);
        vivzHelper = new TraceBookAdapter(this);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = user_email.getText().toString(); //mobileAndEmail
                String upass = user_password.getText().toString();
                String user_email = vivzHelper.getEmail();
                String user_mobile = vivzHelper.getMobileNo();
                String user_password = vivzHelper.getPassword();



                if (uname.equals("a") || upass.equals("a")) {
                    Toast.makeText(getApplicationContext(), "Either user mail or password not provided...", Toast.LENGTH_SHORT).show();
                } else if (((uname.equals(user_email))&& (upass.equals(user_password)))|| ((uname.equals(user_mobile)) && (upass.equals(user_password)))||(uname.equals("") && upass.equals(""))) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserHome.class);
                    startActivity(intent);



                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentilas...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);

            }
        });
    }

}