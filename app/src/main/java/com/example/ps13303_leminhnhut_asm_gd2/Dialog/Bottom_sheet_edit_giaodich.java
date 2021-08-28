package com.example.ps13303_leminhnhut_asm_gd2.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanchi;
import com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Bottom_sheet_edit_giaodich extends BottomSheetDialogFragment {
    EditText edt_tieude,edt_tien,edt_mota;
    TextView tv_ngay,tv_trangthais;
    Spinner sp_pl_giaodich;
    Button btn_update_giaodich;
    Giaodich_DAO giaodich_dao;
    ArrayList<Phanloai> ds_loai_thu;
    ArrayList<Giaodich> ds_thu;
    Adapter_sp_thu adapterSpThu;
    int id;
    String trangthai_;

    public Bottom_sheet_edit_giaodich() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_edit_giaodich, container, false);
       edt_tieude = view.findViewById(R.id.etTieuDe);
       edt_tien = view.findViewById(R.id.etTien);
       edt_mota = view.findViewById(R.id.etMoTa);
       tv_ngay = view.findViewById(R.id.tvNgay);
       tv_trangthais = view.findViewById(R.id.tvTrangThaiGiaoDich);
       sp_pl_giaodich = view.findViewById(R.id.spnGiaoDich);
       btn_update_giaodich = view.findViewById(R.id.btnGiaoDich);

        Bundle mArgs = getArguments();
        id = Integer.parseInt(mArgs.getString("MaGD"));
        final String tieu_de = mArgs.getString("Tieude");
        String ngay = mArgs.getString("Ngay");
        double tien = mArgs.getDouble("Tien");
        String mota = mArgs.getString("MoTa");
        String maloai = mArgs.getString("Maloai");

        edt_tieude.setText(tieu_de);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(tien);

        edt_tien.setText(formattedString);
        edt_tien.addTextChangedListener(onTextChangedListener());
        edt_mota.setText(mota);
        tv_ngay.setText(ngay);

        //getBundle
        trangthai_= mArgs.getString("trangthai");
        tv_trangthais.setText(trangthai_);

       giaodich_dao = new Giaodich_DAO(getContext());
       ds_loai_thu = new ArrayList<>();
        ds_loai_thu = new ArrayList<>();
        if (trangthai_.equals("Thu")){
            ds_loai_thu = giaodich_dao.getThu();
        } else if (trangthai_.equals("Chi")){
            ds_loai_thu = giaodich_dao.getChi();
        }
       String ten_ = giaodich_dao.getTen(maloai);

       adapterSpThu = new Adapter_sp_thu(ds_loai_thu,getContext());
       sp_pl_giaodich.setAdapter(adapterSpThu);

        for(int i = 0; i < ds_loai_thu.size(); i++){
            if(ds_loai_thu.get(i).getTenLoai().equals(ten_)){
                sp_pl_giaodich.setSelection(i);
                break;
            }
        }
        //selectSpinnerValue(sp_pl_giaodich,maloai);
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


        btn_update_giaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String Ngay = tv_ngay.getText().toString();
                String date = tv_ngay.getText().toString();
                String tieude = edt_tieude.getText().toString();

                String str = edt_tien.getText().toString();
                DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                format.setParseBigDecimal(true);
                BigDecimal number = null;
                try {
                     number = (BigDecimal) format.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int so = Integer.parseInt(number+"");

                //String phanloai = sp_pl_giaodich.getSelectedItem().toString();
                String mota = edt_mota.getText().toString();
                int index = sp_pl_giaodich.getSelectedItemPosition();
                int Maloai = ds_loai_thu.get(index).getIdPhanLoai();

                Giaodich gd = new Giaodich(id,tieude,date,so,mota,Maloai);
                giaodich_dao = new Giaodich_DAO(getContext());
                giaodich_dao.update(gd);

                capnhat(trangthai_);
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    public void capnhat(String trangthai){
        ds_thu = giaodich_dao.getKhoanThu_Chi(trangthai);
        Tab_Khoanthu.giaodich_adapter = new Giaodich_Adapter(ds_thu, getContext());
        if (trangthai.equals("Thu")){
            Tab_Khoanthu.rv_thu.setAdapter(Tab_Khoanthu.giaodich_adapter);
        } else if (trangthai.equals("Chi")){
            Tab_Khoanchi.rv_chi.setAdapter(Tab_Khoanthu.giaodich_adapter);
        }
    }


    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edt_tien.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edt_tien.setText(formattedString);
                    edt_tien.setSelection(edt_tien.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                edt_tien.addTextChangedListener(this);
            }
        };
    }



}
