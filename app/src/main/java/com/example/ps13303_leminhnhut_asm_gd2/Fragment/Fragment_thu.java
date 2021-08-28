package com.example.ps13303_leminhnhut_asm_gd2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ps13303_leminhnhut_asm_gd2.Adapter.TabAdapter;
import com.example.ps13303_leminhnhut_asm_gd2.R;
import com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Khoanthu;
import com.example.ps13303_leminhnhut_asm_gd2.TabFragmnet.Tab_Loaithu;
import com.google.android.material.tabs.TabLayout;

public class Fragment_thu extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, container, false);

        viewPager =  view.findViewById(R.id.viewPager);
        tabLayout =  view.findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab_Khoanthu(), "Khoản thu");
        adapter.addFragment(new Tab_Loaithu(), "Loại thu");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
