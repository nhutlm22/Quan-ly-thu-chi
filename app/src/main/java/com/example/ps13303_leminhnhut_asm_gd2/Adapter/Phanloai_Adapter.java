package com.example.ps13303_leminhnhut_asm_gd2.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps13303_leminhnhut_asm_gd2.DAO.Phanloai_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Dialog.Bottom_sheet_edit_pl;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Phanloai;
import com.example.ps13303_leminhnhut_asm_gd2.R;

import java.util.ArrayList;

import static com.example.ps13303_leminhnhut_asm_gd2.Fragment.Fragment_phanloai.phanloai_adapters;
import static com.example.ps13303_leminhnhut_asm_gd2.Fragment.Fragment_phanloai.rv_phanloai;

public class Phanloai_Adapter extends RecyclerView.Adapter<Phanloai_Adapter.MyViewHolder> {
    private ArrayList<Phanloai> ds_phanloai;
    private Context context;
    Phanloai_DAO phanloai_dao;

    public Phanloai_Adapter(ArrayList<Phanloai> ds_phanloai, Context context) {
        this.ds_phanloai = ds_phanloai;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ten;
        public TextView tv_trangthai;
        private ImageView img_xoa_pl;
        private ImageView img_edit_pl;
        public MyViewHolder(View v) {
            super(v);
            tv_ten = v.findViewById(R.id.tvTenLoai);
            tv_trangthai = v.findViewById(R.id.tvTrangThai);
            img_xoa_pl = v.findViewById(R.id.ivDeletePhanLoai);
            img_edit_pl = v.findViewById(R.id.ivEditPhanLoai);
        }
    }


    @Override
    public Phanloai_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_ten.setText(ds_phanloai.get(position).getTenLoai());
        holder.tv_trangthai.setText(ds_phanloai.get(position).getTrangThai());
        holder.img_xoa_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa? " + " " +ds_phanloai.get(position).getTenLoai());
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                phanloai_dao = new Phanloai_DAO(context);
                                phanloai_dao.delete(ds_phanloai.get(position).getIdPhanLoai());
                                Toast.makeText(context, "Xóa thành công " + " " +ds_phanloai.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
                                capnhat();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("id", ds_phanloai.get(position).getIdPhanLoai()+"");
                args.putString("tenloai", ds_phanloai.get(position).getTenLoai()+"");
                args.putString("trangthai", ds_phanloai.get(position).getTrangThai()+"");

                Bottom_sheet_edit_pl bottom_sheet = new Bottom_sheet_edit_pl();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_phanloai.size();
    }
    public void capnhat(){
        ds_phanloai = phanloai_dao.getAll();
        phanloai_adapters = new Phanloai_Adapter(ds_phanloai, context);
        rv_phanloai.setAdapter(phanloai_adapters);
    }
}

