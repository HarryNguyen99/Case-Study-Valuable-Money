package com.example.valuablemoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valuablemoney.AddKhoanThu;
import com.example.valuablemoney.model.KhoanThu;
import com.example.valuablemoney.R;
import java.util.List;


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
        ImageView img_editThu, img_deleteThu;
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
            holder.img_editThu = (ImageView) view.findViewById(R.id.img_editThu);
            holder.img_deleteThu = (ImageView) view.findViewById(R.id.img_deleteThu);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final KhoanThu khoanThu = khoanThuList.get(position);

        holder.tv_NguonThu.setText(khoanThu.getNguonthu());
        holder.tv_TienThu.setText((CharSequence) khoanThu.getSotien());

        return view;
    }



}
