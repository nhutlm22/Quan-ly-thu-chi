package com.example.ps13303_leminhnhut_asm_gd2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ps13303_leminhnhut_asm_gd2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_thongke extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigation);
        if (savedInstanceState == null){
            loadFragment(new Fragment_thongke_thu());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bottomThu:
                        fragment = new Fragment_thongke_thu();
                        loadFragment(fragment);
                        return true;
                    case R.id.bottomChi:
                        fragment = new Fragment_thongke_chi();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayoutBottom, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
