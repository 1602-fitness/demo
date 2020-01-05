package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class homepage extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setContentView(R.layout.activity_main);
                    BottomNavigationView navView1 = findViewById(R.id.nav_view);
                    navView1.getMenu().getItem(0).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView1.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;
                case R.id.navigation_dashboard:
                    setContentView(R.layout.activity_homepage);
                    BottomNavigationView navView2 = findViewById(R.id.nav_view);
                    navView2.getMenu().getItem(1).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                    


                    return true;
                case R.id.navigation_notifications:
                    setContentView(R.layout.activity_main);
                    BottomNavigationView navView3 = findViewById(R.id.nav_view);
                    navView3.getMenu().getItem(2).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView3.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.getMenu().getItem(1).setChecked(true);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
