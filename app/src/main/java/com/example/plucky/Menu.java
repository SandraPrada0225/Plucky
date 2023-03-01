package com.example.plucky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.plucky.Clases.Clan;
import com.example.plucky.ViewModel.ViewModelPlaceholderFragment;
import com.example.plucky.ViewModel.ViewModelRegistro;
import com.example.plucky.ViewModel.ViewModelSplash;
import com.example.plucky.ui.deslizar.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewModelPlaceholderFragment viewModelPlaceholderFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_menu);
        sectionsPagerAdapter = new SectionsPagerAdapter(Menu.this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        viewModelPlaceholderFragment = new ViewModelProvider(this).get(ViewModelPlaceholderFragment.class);


        viewPager.setCurrentItem(2);

        navView.getMenu().findItem(R.id.navigationMundo).setChecked(true);
        //Opciones del menu
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigationTienda:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigationEquipo:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigationMundo:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.navigationOnline:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.navigationTorneo:
                        viewPager.setCurrentItem(4);
                        break;

                }
                return true;
            }
        });

        // asignacion de botones con ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navView.getMenu().findItem(R.id.navigationTienda).setChecked(true);
                        break;
                    case 1:
                        navView.getMenu().findItem(R.id.navigationEquipo).setChecked(true);
                        break;
                    case 2:
                        navView.getMenu().findItem(R.id.navigationMundo).setChecked(true);
                        break;
                    case 3:
                        navView.getMenu().findItem(R.id.navigationOnline).setChecked(true);
                        break;
                    case 4:
                        navView.getMenu().findItem(R.id.navigationTorneo).setChecked(true);
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }
}