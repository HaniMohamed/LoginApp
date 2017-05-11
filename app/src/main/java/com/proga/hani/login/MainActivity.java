package com.proga.hani.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    LinearLayout signUpLt, createLt, signLt;
    Button login, signIn, signUp, create;
    EditText email, password, name, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        final DatabaseHandler db = new DatabaseHandler(this);
        final List<Accounts> accounts = db.getAllAccounts();


        login = (Button) findViewById(R.id.bt_login);
        signUp = (Button) findViewById(R.id.bt_signup);
        create = (Button) findViewById(R.id.bt_create);
        signIn = (Button) findViewById(R.id.bt_sign_in);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        name = (EditText) findViewById(R.id.et_name);
        phone = (EditText) findViewById(R.id.et_phone);

        signUpLt = (LinearLayout) findViewById(R.id.lt_name);
        createLt = (LinearLayout) findViewById(R.id.lt_create);
        signLt = (LinearLayout) findViewById(R.id.lt_sign);

        login.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.buttoncolor));
        signUp.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.buttoncolor));
                signUp.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                signUpLt.setVisibility(View.GONE);
                createLt.setVisibility(View.GONE);
                signLt.setVisibility(View.VISIBLE);

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean found = false;

                for (Accounts cn : accounts) {
                    if (email.getText().toString().equals(cn.getEmail()) && password.getText().toString().equals(cn.getPassword())) {
                        found = true;
                        Intent i = new Intent(MainActivity.this, LoginWelcomeActivity.class);
                        i.putExtra("name", cn.getName());
                        i.putExtra("mail", email.getText().toString());
                        i.putExtra("phone", cn.getPhoneNumber());
                        startActivity(i);
                    }
                }

                if (!found) {
                    Toast.makeText(MainActivity.this, "Sorry, Account not Exist !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.buttoncolor));
                login.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                signUpLt.setVisibility(View.VISIBLE);
                createLt.setVisibility(View.VISIBLE);
                signLt.setVisibility(View.GONE);
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") && checkEmail(email.getText().toString().trim()) && checkPass(password.getText().toString().trim()) && !phone.getText().toString().equals("")) {

                    // Inserting Contacts
                    db.addAccount(new Accounts(name.getText().toString(), password.getText().toString(), phone.getText().toString(), email.getText().toString()));

                    Intent i = new Intent(MainActivity.this,LoginWelcomeActivity.class);
                    i.putExtra("name",name.getText().toString());
                    i.putExtra("mail",email.getText().toString());
                    i.putExtra("phone",phone.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Please Check your form !!", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public  boolean checkEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    public  boolean checkPass(String pass){



        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pass);

        if (!matcher.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid password", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;

    }


}
