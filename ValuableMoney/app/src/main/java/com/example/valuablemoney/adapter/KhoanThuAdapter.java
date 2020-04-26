package com.example.valuablemoney.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valuablemoney.AddKhoanThu;
import com.example.valuablemoney.model.KhoanThu;
import com.example.valuablemoney.R;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class KhoanThuAdapter extends BaseAdapter {

    private AddKhoanThu context;
    private int layout;
    private List<KhoanThu> khoanThuList;

    public KhoanThuAdapter(AddKhoanThu context, int layout, List<KhoanThu> khoanThuList) {
        this.context = context;
        this.layout = layout;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder{
        TextView tv_NguonThu, tv_TienThu;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.dong_thu, parent,false);
            holder = new ViewHolder();
            holder.tv_NguonThu = (TextView) view.findViewById(R.id.tv_NguonThu);
            holder.tv_TienThu = (TextView) view.findViewById(R.id.tv_TienThu);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final KhoanThu khoanThu = khoanThuList.get(position);

        holder.tv_NguonThu.setText(khoanThu.getNguonthu());
        holder.tv_TienThu.setText(formatVND(Long.parseLong(khoanThu.getSotien())));

        return view;
    }

    private String formatVND(long tien){
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(tien);
    }


}
