package com.proga.hani.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoginWelcomeActivity extends AppCompatActivity {

    TextView name, mail, phone;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in=new Intent(LoginWelcomeActivity.this,MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(in);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_welcome);
        Intent i = getIntent();
        String nametxt = i.getStringExtra("name");
        String mailtxt = i.getStringExtra("mail");
        String phonetxt = i.getStringExtra("phone");

        name = (TextView) findViewById(R.id.tv_name);
        name.setText(name.getText() + " " + nametxt);

        mail = (TextView) findViewById(R.id.tv_email);
        mail.setText(mail.getText() + " " + mailtxt);

        phone = (TextView) findViewById(R.id.tv_phone);
        phone.setText(phone.getText() + " " + phonetxt);

    }
}

