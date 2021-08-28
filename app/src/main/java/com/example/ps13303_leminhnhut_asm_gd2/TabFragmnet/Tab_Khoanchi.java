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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Khoanchi extends Fragment {
    FloatingActionButton fl_khoanchi;
    public static RecyclerView rv_chi;
    public static Giaodich_Adapter giaodich_adapter;
    public static ArrayList<Giaodich> ds_khoanchi;
    Giaodich_DAO giaodich_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoan_chi, container, false);
        rv_chi = view.findViewById(R.id.rvKhoanChi);
        rv_chi.setLayoutManager(new LinearLayoutManager(getContext()));
        fl_khoanchi = view.findViewById(R.id.fabKhoanChi);
        fl_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trangthai", "Chi");

                Bottom_sheet_them_giaodich bottom_sheet = new Bottom_sheet_them_giaodich();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(bundle);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });

        ds_khoanchi = new ArrayList<>();
        giaodich_dao = new Giaodich_DAO(getContext());

        ds_khoanchi = giaodich_dao.getKhoanThu_Chi("Chi");
        giaodich_adapter = new Giaodich_Adapter(ds_khoanchi, getContext());
        rv_chi.setAdapter(giaodich_adapter);

        return view;
    }
}
