package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.valuablemoney.adapter.KhoanThuAdapter;
import com.example.valuablemoney.data.DatabaseKhoanThu;
import com.example.valuablemoney.model.KhoanThu;

import java.util.List;

public class AddKhoanThu extends AppCompatActivity {
    Button btn_Them, btn_Huy;
    DatabaseKhoanThu databaseKhoanThu;
    EditText edt_Nguonthu, edt_sotien, edit_Id;
    TextView tv_Thu;
    ListView lv_Thu;
    KhoanThuAdapter adapterThu;
    List<KhoanThu> khoanThuList;


    private void AnhXa() {
        edt_Nguonthu = findViewById(R.id.edt_nguonthu);
        edt_sotien = findViewById(R.id.edt_sotien);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        tv_Thu = findViewById(R.id.tv_KhoanThu);
        databaseKhoanThu = new DatabaseKhoanThu(this);
        lv_Thu = findViewById(R.id.lv_Thu);
        khoanThuList = databaseKhoanThu.getAllKhoanThu();
        lv_Thu.setAdapter(adapterThu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thu);
        AnhXa();
        KhoanThu();
        Huy();
        setAdapterThu();
    }

    private void KhoanThu() {

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanThu khoanThu = createKhoanThu();
                if (khoanThu != null) {
                    databaseKhoanThu.addKhoanThu(khoanThu);
                }
                khoanThuList.clear();
                khoanThuList.addAll(databaseKhoanThu.getAllKhoanThu());
                setAdapterThu();

                edt_Nguonthu.getText().clear();
                edt_sotien.getText().clear();

            }
        });


    }

    private void Huy() {
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private KhoanThu createKhoanThu() {

        String nguonthu = edt_Nguonthu.getText().toString();
        String sotien = edt_sotien.getText().toString();

        KhoanThu khoanThu = new KhoanThu(nguonthu, sotien);
        return khoanThu;
    }

    private void setAdapterThu() {
        if (adapterThu == null) {
            adapterThu = new KhoanThuAdapter(this, R.layout.dong_thu, khoanThuList);
            lv_Thu.setAdapter(adapterThu);
        } else {
            adapterThu.notifyDataSetChanged();
            lv_Thu.setSelection(adapterThu.getCount() - 1);
        }

    }



}
