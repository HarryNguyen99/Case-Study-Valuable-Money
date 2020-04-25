package com.example.valuablemoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valuablemoney.R;
import com.example.valuablemoney.model.KhoanChi;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class KhoanChiAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<KhoanChi> khoanChiList;

    public KhoanChiAdapter(Context context, int layout, List<KhoanChi> khoanChiList) {
        this.context = context;
        this.layout = layout;
        this.khoanChiList = khoanChiList;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder{
        TextView tv_LyDoChi, tv_TienChi;
//        ImageView img_editChi, img_deleteChi;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.dong_chi, parent,false);
            holder = new ViewHolder();
            holder.tv_LyDoChi = (TextView) view.findViewById(R.id.tv_LyDoChi);
            holder.tv_TienChi = (TextView) view.findViewById(R.id.tv_TienChi);
//            holder.img_deleteChi = (ImageView) view.findViewById(R.id.img_deleteChi);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        KhoanChi khoanChi = khoanChiList.get(position);

        holder.tv_LyDoChi.setText(khoanChi.getLydochi());
        holder.tv_TienChi.setText(formatVND(Long.parseLong(khoanChi.getSotienchi())));
        return view;
    }

    private String formatVND(long tien){
        Locale locale = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(tien);
    }

}
