package com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps13303_leminhnhut_asm_gd2.Adapter.Giaodich_Adapter;
import com.example.ps13303_leminhnhut_asm_gd2.DAO.Giaodich_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Dialog.Bottom_sheet_them_giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.R;
import com.example.ps13303_leminhnhut_asm_gd2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Khoanthu extends Fragment {
    FloatingActionButton fl_khoanthu;
    public static RecyclerView rv_thu;
    public static Giaodich_Adapter giaodich_adapter;
    public static ArrayList<Giaodich> ds_khoanthu;
    Giaodich_DAO giaodich_dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoan_thu, container, false);
        fl_khoanthu = view.findViewById(R.id.fabKhoanThu);
        rv_thu = view.findViewById(R.id.rvKhoanThu);
        rv_thu.setLayoutManager(new LinearLayoutManager(getContext()));

        fl_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Thu");

                Bottom_sheet_them_giaodich bottom_sheet = new Bottom_sheet_them_giaodich();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                 bottom_sheet.setArguments(args);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });

        ds_khoanthu = new ArrayList<>();
        giaodich_dao = new Giaodich_DAO(getContext());

        ds_khoanthu = giaodich_dao.getKhoanThu_Chi("Thu");
        giaodich_adapter = new Giaodich_Adapter(ds_khoanthu, getContext());
        rv_thu.setAdapter(giaodich_adapter);


        return view;
    }
}
