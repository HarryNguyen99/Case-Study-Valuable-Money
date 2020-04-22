package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.valuablemoney.adapter.KhoanChiAdapter;
import com.example.valuablemoney.data.DatabaseKhoanChi;
import com.example.valuablemoney.model.KhoanChi;

import java.util.List;

public class AddKhoanChi extends AppCompatActivity {

    Button btn_Them, btn_Huy;
    DatabaseKhoanChi databaseKhoanChi;
    EditText edt_LyDoChi, edt_SoTienChi;
    TextView tv_Chi;
    ListView lv_Chi;
    KhoanChiAdapter adapterChi;
    List<KhoanChi> khoanChiList;

    private void AnhXa() {
        edt_LyDoChi = findViewById(R.id.edt_LyDoChi);
        edt_SoTienChi = findViewById(R.id.edt_SoTienChi);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        tv_Chi = findViewById(R.id.tv_KhoanChi);
        lv_Chi = findViewById(R.id.lv_Chi);
        databaseKhoanChi = new DatabaseKhoanChi(this);
        khoanChiList = databaseKhoanChi.getAllKhoanChi();
        lv_Chi.setAdapter(adapterChi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chi);

        AnhXa();
        KhoanChi();
        Huy();
        setAdapterChi();
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

    private void KhoanChi() {

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanChi khoanChi = createKhoanChi();
                if (khoanChi != null) {
                    databaseKhoanChi.addKhoanChi(khoanChi);
                }

                khoanChiList.clear();
                khoanChiList.addAll(databaseKhoanChi.getAllKhoanChi());
                setAdapterChi();

                edt_SoTienChi.getText().clear();
                edt_SoTienChi.getText().clear();
            }
        });



    }

    private KhoanChi createKhoanChi() {

        String lydochi = edt_LyDoChi.getText().toString();
        String sotienchi = edt_SoTienChi.getText().toString();

        KhoanChi khoanChi = new KhoanChi(lydochi, sotienchi);
        return khoanChi;
    }

    private void setAdapterChi(){
        if (adapterChi == null){
            adapterChi = new KhoanChiAdapter(this,R.layout.dong_chi, khoanChiList);
            lv_Chi.setAdapter(adapterChi);
        }else {
            adapterChi.notifyDataSetChanged();
            lv_Chi.setSelection(adapterChi.getCount()-1);
        }

    }
}
