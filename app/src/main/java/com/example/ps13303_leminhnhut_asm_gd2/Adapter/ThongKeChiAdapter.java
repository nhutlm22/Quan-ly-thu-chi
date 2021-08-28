package com.example.ps13303_leminhnhut_asm_gd2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps13303_leminhnhut_asm_gd2.DAO.Giaodich_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ThongKeChiAdapter extends RecyclerView.Adapter<ThongKeChiAdapter.MyViewHolder> {
    private ArrayList<Giaodich> danhSachChi;
    private Context context;
    Giaodich_DAO giaodich_dao;

    public ThongKeChiAdapter(ArrayList<Giaodich> danhSachChi, Context context) {
        this.danhSachChi = danhSachChi;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        private ImageView img_xoa_thu;
        private ImageView img_edit_thu;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tvTieuDe);
            tv_ngay = v.findViewById(R.id.tvNgay);
            tv_tien = v.findViewById(R.id.tvTienGiaoDich);
            img_xoa_thu = v.findViewById(R.id.ivDeleteThu);
            img_edit_thu = v.findViewById(R.id.ivEditThu);
        }
    }

    @Override
    public ThongKeChiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giaodich, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeChiAdapter.MyViewHolder holder, int position) {
        holder.tv_tieude.setText(danhSachChi.get(position).getTieuDe());
        holder.tv_ngay.setText(danhSachChi.get(position).getNgay()+"");
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(danhSachChi.get(position).getTien());
        holder.tv_tien.setText(s);
    }

    @Override
    public int getItemCount() {
        return danhSachChi.size();
    }

}

