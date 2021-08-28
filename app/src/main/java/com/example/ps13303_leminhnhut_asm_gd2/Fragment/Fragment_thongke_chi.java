package com.example.ps13303_leminhnhut_asm_gd2.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps13303_leminhnhut_asm_gd2.Adapter.ThongKeThuAdapter;
import com.example.ps13303_leminhnhut_asm_gd2.DAO.Giaodich_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_thongke_chi extends Fragment {
    Button btn_date1, btn_date2;
    TextView tv_total;
    RecyclerView rvThongKeChi;
    public static ThongKeThuAdapter khoanChiAdapter;
    public static ArrayList<Giaodich> danhSachThongKeChi;
    public static ArrayList<Giaodich> danhSachThongKeChi2;
    Giaodich_DAO giaodich_dao;
    double total_1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_chi, container, false);
        btn_date1 = view.findViewById(R.id.btnThongKeChiNgay1);
        btn_date2 = view.findViewById(R.id.btnThongKeChiNgay2);
        tv_total = view.findViewById(R.id.tvTongChi);
        rvThongKeChi = view.findViewById(R.id.rvThongKeChi);
        rvThongKeChi.setLayoutManager(new LinearLayoutManager(getContext()));
        giaodich_dao = new Giaodich_DAO(getContext());

        DecimalFormat formatter = new DecimalFormat("#,###");
        total_1 = giaodich_dao.getTotalchi();
        String s = formatter.format(total_1);
        tv_total.setText("Tổng tiền: "+s+" VNĐ");

        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date1();
            }
        });
        btn_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date2();
            }
        });

        danhSachThongKeChi = new ArrayList<>();
        giaodich_dao = new Giaodich_DAO(getContext());

        danhSachThongKeChi = giaodich_dao.getKhoanThu_Chi("Chi");
        khoanChiAdapter = new ThongKeThuAdapter(danhSachThongKeChi, getContext());
        rvThongKeChi.setAdapter(khoanChiAdapter);
        return view;
    }
    private void Date1(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                calendar.set(i,i1,i2);
                btn_date1.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }

    private void Date2(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                calendar.set(i,i1,i2);
                btn_date2.setText(simpleDateFormat.format(calendar.getTime()));

                danhSachThongKeChi2 = giaodich_dao.get_Tk_Chi(btn_date1.getText().toString(), btn_date2.getText().toString());
                khoanChiAdapter = new ThongKeThuAdapter(danhSachThongKeChi2, getContext());
                rvThongKeChi.setAdapter(khoanChiAdapter);

                String date1 = btn_date1.getText().toString();
                DecimalFormat formatter = new DecimalFormat("#,###");
                double total_2 = giaodich_dao.getTotalchibyDate(date1, simpleDateFormat.format(calendar.getTime()));
                String s = formatter.format(total_2);
                tv_total.setText("Tổng tiền: "+s+" VNĐ");
            }
        },years,months,dayOfWeek);
        datePickerDialog.show();
    }
}
