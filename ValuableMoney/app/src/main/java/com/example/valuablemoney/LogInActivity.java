package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valuablemoney.data.DatabaseAcc;
import com.example.valuablemoney.model.AccountSignUp;

public class LogInActivity extends AppCompatActivity {

    TextView tv_logup;
    Button btn_SignIn;
    EditText edt_user, edt_pass;
    DatabaseAcc db;

    private void AnhXa() {
        tv_logup = (TextView) findViewById(R.id.tv_signup);
        btn_SignIn = (Button) findViewById(R.id.btn_SignIn);
        edt_user = findViewById(R.id.edt_user);
        edt_pass = findViewById(R.id.edt_pass);
        db = new DatabaseAcc(this);
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

    private void Signin() {
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();
                Boolean chkAcc = db.checkAcc(user, pass);
                if (chkAcc) {
                    Toast.makeText(LogInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    edt_user.getText().clear();
                    edt_pass.getText().clear();
                } else {
                    Toast.makeText(LogInActivity.this, "sai user hoặc pass", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
