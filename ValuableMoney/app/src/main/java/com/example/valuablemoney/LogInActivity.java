package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    TextView tv_logup;
    Button btn_SignIn;

    private void AnhXa() {
        tv_logup = (TextView) findViewById(R.id.tv_signup);
        btn_SignIn = (Button) findViewById(R.id.btn_SignIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        AnhXa();
        SignUp();
        Signin();
    }

    private void SignUp() {
        tv_logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Signin(){
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
