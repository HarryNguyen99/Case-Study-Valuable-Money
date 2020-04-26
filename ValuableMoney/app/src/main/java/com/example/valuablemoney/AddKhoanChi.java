package com.example.valuablemoney;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.valuablemoney.model.KhoanThu;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class AddKhoanChi extends AppCompatActivity {

    Button btn_Them, btn_Huy, btn_sua;
    TextView tv_tongChi;
    DatabaseKhoanChi databaseKhoanChi;
    EditText edt_LyDoChi, edt_SoTienChi, edt_id;
    ListView lv_Chi;
    KhoanChiAdapter adapterChi;
    List<KhoanChi> khoanChiList;
    AlertDialog.Builder dialogXoa;

    private void AnhXa() {
        tv_tongChi = findViewById(R.id.tv_tienTongChi);
        edt_LyDoChi = findViewById(R.id.edt_LyDoChi);
        edt_SoTienChi = findViewById(R.id.edt_SoTienChi);
        edt_id = findViewById(R.id.edt_idChi);
        btn_Them = findViewById(R.id.btn_Them);
        btn_Huy = findViewById(R.id.btn_Huy);
        btn_sua = findViewById(R.id.btn_Sua);
        lv_Chi = findViewById(R.id.lv_Chi);
        databaseKhoanChi = new DatabaseKhoanChi(this);
        khoanChiList = databaseKhoanChi.getAllKhoanChi();
        lv_Chi.setAdapter(adapterChi);
        dialogXoa = new AlertDialog.Builder(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chi);

        AnhXa();
        KhoanChi();
        sapXepListView ();
        Huy();
        setAdapterChi();
        editKhoanChi();
        deleteChi ();
        tongChi();
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
                sapXepListView ();
                setAdapterChi();
                tongChi();

                edt_LyDoChi.getText().clear();
                edt_SoTienChi.getText().clear();
            }
        });



    }

    private KhoanChi createKhoanChi() {
        KhoanChi khoanChi = null;
        String lydochi = edt_LyDoChi.getText().toString();
        String sotienchi = edt_SoTienChi.getText().toString();
        if (lydochi.equals("") || sotienchi.equals("")){
            Toast.makeText(this, "Không để để trống", Toast.LENGTH_SHORT).show();
        }else if (Integer.parseInt(sotienchi) <= 0){
            Toast.makeText(this, "Nhập sai số tiền", Toast.LENGTH_SHORT).show();
        }else {
            khoanChi = new KhoanChi(lydochi, sotienchi);
        }
        return khoanChi;
    }

    private void setAdapterChi(){
        if (adapterChi == null){
            adapterChi = new KhoanChiAdapter(this,R.layout.dong_chi, khoanChiList);
            lv_Chi.setAdapter(adapterChi);
        }else {
            adapterChi.notifyDataSetChanged();
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
                    sapXepListView();
                    tongChi();
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                dialogXoa.setMessage("Bạn có muốn xóa không?");
                dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        KhoanChi khoanChi = khoanChiList.get(position);
                        databaseKhoanChi.deleteChi(khoanChi.getId());
                        updateListKhoanChi();
                        tongChi();
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

    private void sapXepListView (){
        Collections.sort(khoanChiList, new Comparator<KhoanChi>() {
            @Override
            public int compare(KhoanChi o1, KhoanChi o2) {
                if (o1.getId() < o2.getId()){
                    return 1;
                }else if (o1.getId() > o2.getId()){
                    return -1;
                }else {
                    return 0;
                }
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

    private void tongChi() {
        List<KhoanChi> listKC = databaseKhoanChi.getAllKhoanChi();
        long tienChi = 0;
        for (KhoanChi kc : listKC) {
            tienChi = tienChi + Long.parseLong(kc.getSotienchi());
        }
        tv_tongChi.setText(formatVND(tienChi));
    }

    private String formatVND(long tiente) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(tiente);
    }
}
