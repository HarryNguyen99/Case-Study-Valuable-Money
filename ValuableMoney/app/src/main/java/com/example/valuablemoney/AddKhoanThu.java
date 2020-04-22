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

import com.example.valuablemoney.adapter.KhoanThuAdapter;
import com.example.valuablemoney.data.DatabaseKhoanThu;
import com.example.valuablemoney.model.KhoanThu;

import java.util.List;

public class AddKhoanThu extends AppCompatActivity {
    Button btn_Them, btn_Huy, btn_XacNhan;
    DatabaseKhoanThu databaseKhoanThu;
    EditText edt_Nguonthu, edt_sotien, edit_Id;
    TextView tv_Thu;
    ListView lv_Thu;
    KhoanThuAdapter adapterThu;
    List<KhoanThu> khoanThuList;

    private void AnhXa() {
        edt_Nguonthu = findViewById(R.id.edt_nguonthu);
        edt_sotien = findViewById(R.id.edt_sotien);
        edit_Id = findViewById(R.id.edt_id);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        tv_Thu = findViewById(R.id.tv_KhoanThu);
        btn_XacNhan = findViewById(R.id.btn_XacNhan);
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
        editKhoanThu();
        deleteThu();
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

    private void editKhoanThu() {
        lv_Thu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KhoanThu khoanThu = khoanThuList.get(position);
                edit_Id.setText(String.valueOf(khoanThu.getId()));
                edt_Nguonthu.setText(khoanThu.getNguonthu());
                edt_sotien.setText(khoanThu.getSotien());
                btn_Them.setEnabled(false);
                btn_XacNhan.setEnabled(true);
            }
        });

        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setId(Integer.parseInt(String.valueOf(edit_Id.getText())));
                khoanThu.setNguonthu(edt_Nguonthu.getText() + "");
                khoanThu.setSotien(edt_sotien.getText() + "");
                int result = databaseKhoanThu.EditKhoanThu(khoanThu);
                if (result > 0) {
                    updateListKhoanThu();
                    edit_Id.getText().clear();
                    edt_Nguonthu.getText().clear();
                    edt_sotien.getText().clear();
                    btn_Them.setEnabled(true);
                    btn_XacNhan.setEnabled(false);
                } else {
                    btn_Them.setEnabled(true);
                    btn_XacNhan.setEnabled(false);
                }
            }
        });
    }

    private void deleteThu() {
        lv_Thu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                KhoanThu khoanThu = khoanThuList.get(position);
                int result = databaseKhoanThu.deleteThu(khoanThu.getId());
                if (result > 0) {
                    Toast.makeText(AddKhoanThu.this, "Đã Xóa " + khoanThu.getNguonthu()
                            + " Thành Công", Toast.LENGTH_SHORT).show();
                    updateListKhoanThu();
                } else {
                    Toast.makeText(AddKhoanThu.this, "Đã Xóa " + khoanThu.getNguonthu()
                            + " Thất Bại", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    public void updateListKhoanThu () {
            khoanThuList.clear();
            khoanThuList.addAll(databaseKhoanThu.getAllKhoanThu());
            if (adapterThu != null) {
                adapterThu.notifyDataSetChanged();
            }
        }
    }

