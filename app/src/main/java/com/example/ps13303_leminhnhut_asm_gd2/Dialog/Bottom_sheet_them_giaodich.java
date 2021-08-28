package com.example.ps13303_leminhnhut_asm_gd2.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ps13303_leminhnhut_asm_gd2.Adapter.Adapter_sp_thu;
import com.example.ps13303_leminhnhut_asm_gd2.Adapter.Giaodich_Adapter;
import com.example.ps13303_leminhnhut_asm_gd2.DAO.Giaodich_DAO;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Giaodich;
import com.example.ps13303_leminhnhut_asm_gd2.Model.Phanloai;
import com.example.ps13303_leminhnhut_asm_gd2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanchi.rv_chi;
import static com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu.giaodich_adapter;
import static com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu.rv_thu;

public class Bottom_sheet_them_giaodich extends BottomSheetDialogFragment {
    EditText edt_tieude,edt_tien,edt_mota;
    TextView tv_ngay, tv_trangthai;
    Spinner sp_pl_giaodich;
    Button btn_them_giaodich;
    Giaodich_DAO giaodich_dao;
    ArrayList<Phanloai> ds_loai_thu;
    ArrayList<Giaodich> ds_thu;
    Adapter_sp_thu adapterSpThu;
    String trangthai_;

    public Bottom_sheet_them_giaodich(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_giaodich, container, false);
       edt_tieude = view.findViewById(R.id.etTieuDe);
       edt_tien = view.findViewById(R.id.etTien);
       edt_mota = view.findViewById(R.id.etMoTa);
       tv_ngay = view.findViewById(R.id.tvNgay);
       tv_trangthai = view.findViewById(R.id.tvTrangThai);
       sp_pl_giaodich = view.findViewById(R.id.spnGiaoDich);
       btn_them_giaodich = view.findViewById(R.id.btnGiaoDich);

       //getBundle
        Bundle getdata = getArguments();
        trangthai_= getdata.getString("trangthai");
        tv_trangthai.setText(trangthai_);

       giaodich_dao = new Giaodich_DAO(getContext());
       ds_loai_thu = new ArrayList<>();
       if (trangthai_.equals("Thu")){
           ds_loai_thu = giaodich_dao.getThu();
       } else if (trangthai_.equals("Chi")){
           ds_loai_thu = giaodich_dao.getChi();
       }


       adapterSpThu = new Adapter_sp_thu(ds_loai_thu,getContext());
       sp_pl_giaodich.setAdapter(adapterSpThu);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

       tv_ngay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final Calendar calendar = Calendar.getInstance();
               int date = calendar.get(Calendar.DAY_OF_MONTH);
               int month = calendar.get(Calendar.MONTH);
               int year = calendar.get(Calendar.YEAR);

               DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                       calendar.set(i,i1,i2);
                       tv_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                   }
               },years,months,dayOfWeek);
               datePickerDialog.show();
           }
       });


        btn_them_giaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = tv_ngay.getText().toString();
                String tieude = edt_tieude.getText().toString();
                try {
                    double tien = Double.parseDouble(edt_tien.getText().toString());

                String mota = edt_mota.getText().toString();
                int index = sp_pl_giaodich.getSelectedItemPosition();
                int Maloai = ds_loai_thu.get(index).getIdPhanLoai();

                Giaodich gd = new Giaodich(tieude,date,tien,mota,Maloai);
                giaodich_dao = new Giaodich_DAO(getContext());
                giaodich_dao.them(gd);
                capnhat(trangthai_);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin!", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return view;
    }

    public void capnhat(String trangthai){
        ds_thu = giaodich_dao.getKhoanThu_Chi(trangthai);
        giaodich_adapter = new Giaodich_Adapter(ds_thu, getContext());
        if (trangthai.equals("Thu")){
            rv_thu.setAdapter(giaodich_adapter);
        } else if (trangthai.equals("Chi")){
            rv_chi.setAdapter(giaodich_adapter);
        }
    }
}
