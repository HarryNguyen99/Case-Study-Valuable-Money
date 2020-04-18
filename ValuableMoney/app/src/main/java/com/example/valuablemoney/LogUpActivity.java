package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valuablemoney.data.DatabaseAcc;
import com.example.valuablemoney.model.AccountSignUp;

public class LogUpActivity extends AppCompatActivity {

    TextView tv_LogIn;
    Button btn_SignUp;
    EditText edt_User, edt_Pass1, edt_Pass2;
    DatabaseAcc databaseAcc;


    public void AnhXa() {
        tv_LogIn = findViewById(R.id.tv_signin);
        btn_SignUp = findViewById(R.id.btn_SignUp);
        edt_User = findViewById(R.id.edt_UserSignUp);
        edt_Pass1 = findViewById(R.id.edt_pass1);
        edt_Pass2 = findViewById(R.id.edt_pass2);
        databaseAcc =new DatabaseAcc(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);
        AnhXa();
        SignIn();
        SignUp();
    }

    public void SignUp() {
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountSignUp account = createAccount();
                if (account != null){
                    databaseAcc.addAccount(account);
                }

            }
        });

    }

    private AccountSignUp createAccount() {
        String user = edt_User.getText().toString();
        String pass1 = edt_Pass1.getText().toString();
        String pass2 = edt_Pass2.getText().toString();

        AccountSignUp accountSignUp = new AccountSignUp(user,pass1,pass2);
        return accountSignUp;
    }

    private void SignIn() {
        tv_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
