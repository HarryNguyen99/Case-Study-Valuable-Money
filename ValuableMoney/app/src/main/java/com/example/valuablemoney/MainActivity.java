package com.example.valuablemoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.valuablemoney.data.DatabaseKhoanChi;
import com.example.valuablemoney.data.DatabaseKhoanThu;
import com.example.valuablemoney.model.KhoanChi;
import com.example.valuablemoney.model.KhoanThu;

import androidx.appcompat.app.AppCompatActivity;


import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv_Thu, tv_Chi, tvConLai;
    Button btn_Thu, btn_Chi;
    DatabaseKhoanThu databaseKhoanThu;
    DatabaseKhoanChi databaseKhoanChi;

    private void AnhXa() {
        tv_Thu = findViewById(R.id.tv_KhoanThu);
        tv_Chi = findViewById(R.id.tv_KhoanChi);
        tvConLai = findViewById(R.id.tv_ConLai);
        btn_Thu = findViewById(R.id.btn_KhoanThu);
        btn_Chi = findViewById(R.id.btn_KhoanChi);
        databaseKhoanThu = new DatabaseKhoanThu(this);
        databaseKhoanChi = new DatabaseKhoanChi(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        KhoanThu();
        KhoanChi();
        tongThu();
        tongChi();
        soTienConLai();
    }

    private void KhoanThu() {

        btn_Thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddKhoanThu.class);
                startActivity(intent);

            }
        });

    }

    private void KhoanChi() {
        btn_Chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddKhoanChi.class);
                startActivity(intent);
            }
        });
    }

    public void tongThu() {
        List<KhoanThu> lstThu = databaseKhoanThu.getAllKhoanThu();
        long tienThu = 0;
        for (KhoanThu kt : lstThu) {
            tienThu = tienThu + Long.parseLong(kt.getSotien());
        }
        tv_Thu.setText(formatVND(tienThu));
    }

    public void tongChi() {
        List<KhoanChi> lstChi = databaseKhoanChi.getAllKhoanChi();
        long tienChi = 0;
        for (KhoanChi kc : lstChi) {
            tienChi = tienChi + Long.parseLong(kc.getSotienchi());
        }
        tv_Chi.setText(formatVND(tienChi));
    }

    public void soTienConLai() {
        List<KhoanThu> lstThu = databaseKhoanThu.getAllKhoanThu();
        long tienThu = 0;
        for (KhoanThu kt : lstThu) {
            tienThu = tienThu + Long.parseLong(kt.getSotien());
        }

        List<KhoanChi> lstChi = databaseKhoanChi.getAllKhoanChi();
        long tienChi = 0;
        for (KhoanChi kc : lstChi) {
            tienChi = tienChi + Long.parseLong(kc.getSotienchi());
        }

        long conLai = tienThu - tienChi;
        tvConLai.setText(formatVND(conLai));
    }

    private String formatVND(long tien) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(tien);
    }

}
