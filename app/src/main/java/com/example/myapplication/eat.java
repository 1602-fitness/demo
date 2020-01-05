package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class eat extends AppCompatActivity {
    private TextView mTextMessage;
    private weightHelper whelper;
    String str1;
    String str2;
    Float bmi;
    int all=0;
    int energy1=0;
    int energy2=0;
    int energy3=0;
    int add_weight = 46;
    LineChart lineChart;

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
                    Intent intent1 = new Intent(eat.this,eat.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    setContentView(R.layout.activity_homepage);
                    BottomNavigationView navView2 = findViewById(R.id.nav_view);
                    navView2.getMenu().getItem(1).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    Intent intent2 = new Intent(eat.this, homepage2.class);



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
        setContentView(R.layout.eat);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.getMenu().getItem(0).setChecked(true);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button choose1 = (Button) findViewById(R.id.choose1);
        Button choose2 = (Button) findViewById(R.id.choose2);
        Button choose3 = (Button) findViewById(R.id.choose3);
        choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(eat.this, choose.class);
                startActivityForResult(intent,1);
            }
        });
        choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(eat.this, choose.class);
                startActivityForResult(intent,2);
            }
        });
        choose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(eat.this, choose.class);
                startActivityForResult(intent,3);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    ArrayList<String> returnedData = (ArrayList<String>) data.getStringArrayListExtra("data_return");
                    TextView food1 = (TextView) findViewById(R.id.food1);
                    String name = returnedData.get(0);
                    int energy = Integer.parseInt(returnedData.get(1));
                    energy1 = energy;
                    food1.setText(name);
                    break;
                }
            case 2:
                if(resultCode==RESULT_OK){
                    ArrayList<String> returnedData = (ArrayList<String>) data.getStringArrayListExtra("data_return");
                    TextView food2 = (TextView) findViewById(R.id.food2);
                    String name = returnedData.get(0);
                    int energy = Integer.parseInt(returnedData.get(1));
                    food2.setText(name);
                    energy2 = energy;
                    break;
                }
            case 3:
                if(resultCode==RESULT_OK){
                    ArrayList<String> returnedData = (ArrayList<String>) data.getStringArrayListExtra("data_return");
                    TextView food3 = (TextView) findViewById(R.id.food3);
                    String name = returnedData.get(0);
                    int energy = Integer.parseInt(returnedData.get(1));
                    food3.setText(name);
                    energy3 = energy;
                    break;
                }
            default:
                break;
        }
        final Button eat = (Button)findViewById(R.id.eat);
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all = energy1+energy2+energy3;
                TextView top = (TextView) findViewById(R.id.top);
                top.setText("您今日摄入卡路里总计"+all+"千卡");
            }
        });
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String energy = "";
                energy = energy+all;
                Intent intent = new Intent(eat.this,homepage2.class);
                intent.putExtra("extra_data",energy);
                startActivity(intent);
            }
        });
    }
}