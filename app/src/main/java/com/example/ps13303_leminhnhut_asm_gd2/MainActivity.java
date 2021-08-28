package com.example.ps13303_leminhnhut_asm_gd2;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ps13303_leminhnhut_asm_gd2.Fragment.Fragment_phanloai;
import com.example.ps13303_leminhnhut_asm_gd2.Fragment.Fragment_thongke;
import com.example.ps13303_leminhnhut_asm_gd2.Fragment.Fragment_thu_chi;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPinkkk));

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle =setupDrawerToogle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        if (savedInstanceState == null){
            navigationView.setCheckedItem(R.id.khoanThu);
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutContainer, new Fragment_thu_chi()).commit();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.khoanThu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutContainer, new Fragment_thu_chi()).commit();
                        break;
                    case R.id.khoanChi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutContainer, new Fragment_phanloai()).commit();
                        break;
                    case R.id.thongke:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutContainer, new Fragment_thongke()).commit();
                        break;
                    case R.id.gioiThieu:
                        Toast.makeText(MainActivity.this, "Đây là : Giới thiệu", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.thoat:
                        Toast.makeText(MainActivity.this, "Đây là : Thoát", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutContainer, new Fragment_thu_chi()).commit();
                    }

                item.setChecked(true);
                setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.Open,R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}