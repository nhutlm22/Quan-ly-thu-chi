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

import com.example.ps13303_leminhnhut_asm_gd2.DAO.Giaodich_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Dialog.Bottom_sheet_edit_giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu.giaodich_adapter;
import static com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu.rv_thu;

public class Giaodich_Adapter extends RecyclerView.Adapter<Giaodich_Adapter.MyViewHolder> {
    private ArrayList<Giaodich> ds_thu;
    private Context context;
    Giaodich_DAO giaodich_dao;

    public Giaodich_Adapter(ArrayList<Giaodich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
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
    public Giaodich_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giaodich, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_thu.get(position).getTieuDe());
        holder.tv_ngay.setText(ds_thu.get(position).getNgay()+"");
       //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).getTien());
        holder.tv_tien.setText(s);
        holder.img_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa? " +  " " + ds_thu.get(position).getTieuDe());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                giaodich_dao = new Giaodich_DAO(context);
                                giaodich_dao.delete(ds_thu.get(position).getMaGiaoDich());
                                Toast.makeText(context, "Xóa thành công " + " " + ds_thu.get(position).getTieuDe(), Toast.LENGTH_SHORT).show();
                                ds_thu.remove(position);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaGD", ds_thu.get(position).getMaGiaoDich()+"");
                args.putString("Tieude", ds_thu.get(position).getTieuDe()+"");
                args.putString("Ngay", ds_thu.get(position).getNgay()+"");
                args.putString("MoTa", ds_thu.get(position).getMoTa()+"");
                args.putDouble("Tien", ds_thu.get(position).getTien());
                args.putString("Maloai", ds_thu.get(position).getMaLoai()+"");
                giaodich_dao = new Giaodich_DAO(context);
                String th = giaodich_dao.get_trangthai(ds_thu.get(position).getMaGiaoDich(),ds_thu.get(position).getMaLoai());
                args.putString("trangthai", th);

                Bottom_sheet_edit_giaodich bottom_sheet = new Bottom_sheet_edit_giaodich();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_thu.size();
    }

    public void capnhat(){
//        if (giaodich_dao.getTrangthai(ds_thu.get(index).getMaloai()).equals("Thu")){
////            ds_thu = giaodich_dao.getKhoanThu();
////        } else if (giaodich_dao.getTrangthai(ds_thu.get(index).getMaloai()).equals("Chi")){
////            ds_thu = giaodich_dao.getKhoanChi();
////        }

        ds_thu = giaodich_dao.getKhoanThu_Chi("Thu");
        giaodich_adapter = new Giaodich_Adapter(ds_thu, context);
        rv_thu.setAdapter(giaodich_adapter);
    }


}

