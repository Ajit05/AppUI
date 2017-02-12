package com.ui.ajit.appui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateAccount extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePicker datePicker;
    private Calendar calendar;
    private Button dateView;
    private int year, month, day;
    private Spinner gender;
    Toolbar createToolbar = null;
    TraceBookAdapter vivzHelper; //DATABASE SQLITE
    EditText u_fname, u_lname, umobile, uemail, upassword, ucpassword;
    Button udob,user_reg;
    Spinner iam;
    CheckBox terms;
    ImageView navBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toast.makeText(getApplicationContext(),"Welcome, Gets register with us", Toast.LENGTH_LONG).show();
        createToolbar = (Toolbar) findViewById(R.id.createToolbar);
        setSupportActionBar(createToolbar);
        vivzHelper = new TraceBookAdapter(this);




        dateView = (Button) findViewById(R.id.udob);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        //Now we supplie array using adapter
        gender = (Spinner)findViewById(R.id.iam);
        // Spinner click listener
        gender.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");
        categories.add("Others");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender.setAdapter(dataAdapter);


        //Code to get registration page id
        u_fname = (EditText) findViewById(R.id.u_fname);
        u_lname = (EditText) findViewById(R.id.u_lname);
        umobile = (EditText) findViewById(R.id.umobile);
        uemail = (EditText) findViewById(R.id.uemail);
        upassword = (EditText) findViewById(R.id.upassword);
        ucpassword = (EditText) findViewById(R.id.ucpassword);
        udob = (Button) findViewById(R.id.udob);
        iam = (Spinner) findViewById(R.id.iam);
        terms = (CheckBox) findViewById(R.id.terms);
        user_reg = (Button) findViewById(R.id.user_register);
        vivzHelper = new TraceBookAdapter(this);

        //calling
        user_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = u_fname.getText().toString();
                String slname = u_lname.getText().toString();
                String smobile = umobile.getText().toString();
                String semail = uemail.getText().toString();
                String sUserPassword = upassword.getText().toString();
                String sconfirmPassword = ucpassword.getText().toString();
                String sbirthday = udob.getText().toString();
                if (sname.equals("") || slname.equals("") || smobile.equals("") || semail.equals("") ||
                        sUserPassword.equals("") || sconfirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field can't be null", Toast.LENGTH_LONG).show();

                } else {
                    if (!sUserPassword.equals(sconfirmPassword)) {
                        Toast.makeText(getApplicationContext(), "password mismatch!", Toast.LENGTH_LONG).show();

                    } else {
                        if (sUserPassword.equals(smobile) || sUserPassword.equals(semail)) {
                            Toast.makeText(getApplicationContext(), "password cann't be same as email/mobile no!", Toast.LENGTH_LONG).show();
                        } else {
                            String xUniqueId = vivzHelper.getMobileNo();
                            String xUserName = vivzHelper.getEmail();
                            //Message.message(this,xUniqueId);
                            //Message.message(this,xUserName);
                            if ((xUniqueId.equals(smobile))&& (xUserName.equals(semail))) {
                                Toast.makeText(getApplicationContext(), "user already have this mobile register", Toast.LENGTH_LONG).show();
                            } else {
                                long id = vivzHelper.insertData(sname, slname, smobile, semail, sUserPassword, sconfirmPassword);

                                if (id < 0) {
                                    Toast.makeText(getApplicationContext(), "Some Error Encounter", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Sucessfully Signup!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);

                                }
                            }
                        }
                    }
                }
            }


        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}


