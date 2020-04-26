package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valuablemoney.adapter.KhoanThuAdapter;
import com.example.valuablemoney.data.DatabaseKhoanThu;
import com.example.valuablemoney.model.KhoanThu;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AddKhoanThu extends AppCompatActivity {
    TextView tv_TongThu;
    Button btn_Them, btn_Huy, btn_XacNhan;
    ImageView img_Delete;
    DatabaseKhoanThu databaseKhoanThu;
    EditText edt_Nguonthu, edt_sotien, edit_Id;
    ListView lv_Thu;
    KhoanThuAdapter adapterThu;
    List<KhoanThu> khoanThuList;
    AlertDialog.Builder dialogXoa;

    private void AnhXa() {
        tv_TongThu = findViewById(R.id.tv_TongThu);
        edt_Nguonthu = findViewById(R.id.edt_nguonthu);
        edt_sotien = findViewById(R.id.edt_sotien);
        edit_Id = findViewById(R.id.edt_id);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        btn_XacNhan = findViewById(R.id.btn_XacNhan);
        databaseKhoanThu = new DatabaseKhoanThu(this);
        lv_Thu = findViewById(R.id.lv_Thu);
        khoanThuList = databaseKhoanThu.getAllKhoanThu();
        lv_Thu.setAdapter(adapterThu);
        dialogXoa = new AlertDialog.Builder(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thu);
        AnhXa();
        KhoanThu();
        sapXepListView();
        Huy();
        setAdapterThu();
        editKhoanThu();
        deleteThu();
        tongThu();
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
                sapXepListView();
                setAdapterThu();
                tongThu();

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
        KhoanThu khoanThu = null;
        String nguonthu = edt_Nguonthu.getText().toString();
        String sotien = edt_sotien.getText().toString();
        if (nguonthu.equals("") || sotien.equals("")) {
            Toast.makeText(this, "Không để để trống", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(sotien) <= 0) {
            Toast.makeText(this, "Nhập sai số tiền", Toast.LENGTH_SHORT).show();
        } else {
            khoanThu = new KhoanThu(nguonthu, sotien);
        }
        return khoanThu;
    }

    private void setAdapterThu() {
        if (adapterThu == null) {
            adapterThu = new KhoanThuAdapter(this, R.layout.dong_thu, khoanThuList);
            lv_Thu.setAdapter(adapterThu);
        } else {
            adapterThu.notifyDataSetChanged();
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
                    sapXepListView();
                    tongThu();
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

               dialogXoa.setMessage("Bạn có muốn xóa không?");
               dialogXoa.setCancelable(false);
               dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                   @Override
                    public void onClick(DialogInterface dialog, int id) {
                        KhoanThu khoanThu = khoanThuList.get(position);
                        databaseKhoanThu.deleteThu(khoanThu.getId());
                        updateListKhoanThu();
                        tongThu();
                        sapXepListView();
                    }
                });
                dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogXoa.show();

                return false;
            }
        });
    }

    private void sapXepListView() {
        Collections.sort(khoanThuList, new Comparator<KhoanThu>() {
            @Override
            public int compare(KhoanThu o1, KhoanThu o2) {
                if (o1.getId() < o2.getId()) {
                    return 1;
                } else if (o1.getId() > o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void updateListKhoanThu() {
        khoanThuList.clear();
        khoanThuList.addAll(databaseKhoanThu.getAllKhoanThu());
        if (adapterThu != null) {
            adapterThu.notifyDataSetChanged();
        }
    }

    private void tongThu() {
        List<KhoanThu> lst = databaseKhoanThu.getAllKhoanThu();
        long tienThu = 0;
        for (KhoanThu kt : lst) {
            tienThu = tienThu + Long.parseLong(kt.getSotien());
        }
        tv_TongThu.setText("" + formatVND(tienThu));
    }

    private String formatVND(long tiente) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(tiente);
    }

}

