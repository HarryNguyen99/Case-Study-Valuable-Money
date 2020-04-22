package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valuablemoney.adapter.KhoanChiAdapter;
import com.example.valuablemoney.data.DatabaseKhoanChi;
import com.example.valuablemoney.model.KhoanChi;

import java.util.List;

public class AddKhoanChi extends AppCompatActivity {

    Button btn_Them, btn_Huy, btn_sua;
    DatabaseKhoanChi databaseKhoanChi;
    EditText edt_LyDoChi, edt_SoTienChi, edt_id;
    TextView tv_Chi;
    ListView lv_Chi;
    KhoanChiAdapter adapterChi;
    List<KhoanChi> khoanChiList;

    private void AnhXa() {
        edt_LyDoChi = findViewById(R.id.edt_LyDoChi);
        edt_SoTienChi = findViewById(R.id.edt_SoTienChi);
        edt_id = findViewById(R.id.edt_idChi);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        btn_sua = findViewById(R.id.btn_Sua);
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
        editKhoanChi();
        deleteChi ();
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

                edt_LyDoChi.getText().clear();
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

    private void editKhoanChi(){
        lv_Chi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhoanChi khoanChi = khoanChiList.get(position);
                edt_id.setText(String.valueOf(khoanChi.getId()));
                edt_LyDoChi.setText(khoanChi.getLydochi());
                edt_SoTienChi.setText(khoanChi.getSotienchi());
                btn_Them.setEnabled(false);
                btn_sua.setEnabled(true);
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setId(Integer.parseInt(String.valueOf(edt_id.getText())));
                khoanChi.setLydochi(edt_LyDoChi.getText()+"");
                khoanChi.setSotienchi(edt_SoTienChi.getText()+"");
                int result = databaseKhoanChi.EditKhoanChi(khoanChi);
                if (result > 0) {
                    updateListKhoanChi();
                    edt_id.getText().clear();
                    edt_LyDoChi.getText().clear();
                    edt_SoTienChi.getText().clear();
                    btn_Them.setEnabled(true);
                    btn_sua.setEnabled(false);
                }else {
                    btn_Them.setEnabled(true);
                    btn_sua.setEnabled(false);
                }
            }
        });
    }

    private void deleteChi (){
        lv_Chi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                KhoanChi khoanChi = khoanChiList.get(position);
                int result = databaseKhoanChi.deleteChi(khoanChi.getId());
                if (result > 0) {
                    Toast.makeText(AddKhoanChi.this, "Đã Xóa " + khoanChi.getLydochi()
                            + " Thành Công", Toast.LENGTH_SHORT).show();
                    updateListKhoanChi();
                }else {
                    Toast.makeText(AddKhoanChi.this, "Xóa " + khoanChi.getLydochi()
                            + " Thất Bại", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    public void updateListKhoanChi() {
        khoanChiList.clear();
        khoanChiList.addAll(databaseKhoanChi.getAllKhoanChi());
        if (adapterChi != null) {
            adapterChi.notifyDataSetChanged();
        }
    }
}
