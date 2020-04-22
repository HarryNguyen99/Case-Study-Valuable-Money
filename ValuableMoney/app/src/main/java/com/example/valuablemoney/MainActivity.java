package com.example.valuablemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.valuablemoney.adapter.KhoanChiAdapter;
import com.example.valuablemoney.adapter.KhoanThuAdapter;
import com.example.valuablemoney.data.DatabaseKhoanChi;
import com.example.valuablemoney.data.DatabaseKhoanThu;
import com.example.valuablemoney.model.KhoanChi;
import com.example.valuablemoney.model.KhoanThu;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv_Thu, tv_Chi, tvConLai;
    Button btn_Thu, btn_Chi;



    private void AnhXa(){
        tv_Thu = findViewById(R.id.tv_KhoanThu);
        tv_Chi = findViewById(R.id.tv_KhoanChi);
        tvConLai = findViewById(R.id.tv_ConLai);
        btn_Thu = findViewById(R.id.btn_KhoanThu);
        btn_Chi = findViewById(R.id.btn_KhoanChi);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        KhoanThu();
        KhoanChi();
       // FormatNum();
    }

    private void KhoanThu(){

        btn_Thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddKhoanThu.class);
                startActivity(intent);

            }
        });

    }

    private void KhoanChi(){
        btn_Chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddKhoanChi.class);
                startActivity(intent);
            }
        });
    }

//    private String FormatNum(){
//        int TQKhoanThu = tv_Thu;
//        Locale locale = new Locale("vi", "VN");
//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
//        return numberFormat.format(TQKhoanThu);
//    }

}
