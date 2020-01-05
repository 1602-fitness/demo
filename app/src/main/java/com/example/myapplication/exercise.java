package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class exercise extends AppCompatActivity {
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
                    Intent intent = new Intent(exercise.this, eat.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    setContentView(R.layout.activity_homepage);
                    BottomNavigationView navView2 = findViewById(R.id.nav_view);
                    navView2.getMenu().getItem(1).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    Intent intent1 = new Intent(exercise.this, homepage2.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_notifications:
                    setContentView(R.layout.activity_main);
                    BottomNavigationView navView3 = findViewById(R.id.nav_view);
                    navView3.getMenu().getItem(2).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView3.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    Intent intent2 = new Intent(exercise.this, exercise.class);
                    startActivity(intent2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.getMenu().getItem(2).setChecked(true);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ImageButton btn_refresh = (ImageButton) findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomnum = (int) (Math.random() * 100 % 5);
//                Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                TextView title = (TextView) findViewById(R.id.tit);
                TextView content = (TextView) findViewById(R.id.cont);
                switch (randomnum){
                    case 0:
//                        Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                        title.setText("快步走");
                        content.setText("在公园快走，既能锻炼心肺，还能欣赏秋景。快走运动消耗能量多，并且不会对关节造成太大的压力。");
                        break;
                    case 1:
//                        Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                        title.setText("打篮球");
                        content.setText("喜欢团队运动的朋友当然拒绝不了篮球的诱惑。上下肢得到锻炼的同时，心肺功能也能得到提高。");
                        break;
                    case 2:
//                        Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                        title.setText("扔飞盘");
                        content.setText("扔飞盘的过程中需要跑动，因此能锻炼耐力。由于经常跑跑停停、变换方向，所以身体的敏捷度和平衡力也会增强。");
                        break;
                    case 3:
//                        Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                        title.setText("划船");
                        content.setText("划船看似是一种休闲活动，其实它能同时锻炼背部、胸部、腹部的肌肉，是一项很好的有氧运动，有条件的人不妨尝试。");
                        break;
                    case 4:
//                        Toast.makeText(exercise.this, ""+randomnum,Toast.LENGTH_SHORT).show();
                        title.setText("骑车");
                        content.setText("骑行是非常受欢迎的一项运动，不仅能预防早衰，同时还能提高心肺功能，锻炼下肢肌力和增强全身耐力。骑自行车时，由于周期性的有氧运动，使锻炼者消耗较多的热量，还能达到显著的减肥效果。");
                        break;
                    default:
                        break;
                }
            }
        });
        ImageButton btn = (ImageButton) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText path = (EditText) findViewById(R.id.path);
                TextView km = (TextView) findViewById(R.id.km);
                TextView run = (TextView) findViewById(R.id.run);
                TextView rice = (TextView) findViewById(R.id.rice);
                TextView drink = (TextView) findViewById(R.id.drink);
                String s = path.getText().toString();
                Toast.makeText(exercise.this, s,Toast.LENGTH_SHORT).show();
                int key = Integer.parseInt(s);
                int key1 = key/2/1000;
                double k =(double) key * 0.025;
                int key2 = (int) k;
                double key3 = key2/160.0;
                double key4 = key2/500.0;

                km.setText("约"+key1+"公里");
                run.setText("跑步消耗 "+key2+"千卡");
                rice.setText("相当于"+key3+"份米饭");
                drink.setText(key4+"杯奶茶");
            }
        });
    }

}