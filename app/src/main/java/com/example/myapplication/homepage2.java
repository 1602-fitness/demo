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
import com.google.android.material.navigation.NavigationView;

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
import java.util.Date;
import com.example.myapplication.weightHelper;




public class homepage2 extends AppCompatActivity {
    private TextView mTextMessage;
    private weightHelper whelper;
    String str1;
    String str2;
    Float bmi;
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
                    return true;
                case R.id.navigation_dashboard:
                    setContentView(R.layout.activity_homepage);
                    BottomNavigationView navView2 = findViewById(R.id.nav_view);
                    navView2.getMenu().getItem(1).setChecked(true);
                    mTextMessage = findViewById(R.id.message);
                    navView2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                    lineChart= (LineChart) findViewById(R.id.lineChart);

                    Description description =new Description();
                    description.setText("近期体重曲线图");
                    description.setTextColor(Color.RED);
                    description.setTextSize(15);
                    lineChart.setDescription(description);//设置图表描述信息
                    lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
                    lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
                    lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
                    lineChart.setDrawBorders(false);//禁止绘制图表边框的线
                    final ArrayList<Entry> values1 = new ArrayList<>();
                    values1.add(new Entry(1,45));
                    values1.add(new Entry(2,46));
                    values1.add(new Entry(3,47));
                    values1.add(new Entry(4,47));
                    values1.add(new Entry(5,46));
                    values1.add(new Entry(6,45));
                    values1.add(new Entry(7,45));
                    values1.add(new Entry(8,46));
                    values1.add(new Entry(9,45));
                    values1.add(new Entry(10,add_weight));
                    //LineDataSet每一个对象就是一条连接线
                    LineDataSet set1;

                    //判断图表中原来是否有数据
                    if (lineChart.getData() != null &&
                            lineChart.getData().getDataSetCount() > 0) {
                        //获取数据1
                        set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                        set1.setValues(values1);
                        //刷新数据
                        lineChart.getData().notifyDataChanged();
                        lineChart.notifyDataSetChanged();
                    } else {
                        //设置数据1  参数1：数据源 参数2：图例名称
                        set1 = new LineDataSet(values1, "近期体重数据");
                        set1.setColor(Color.BLACK);
                        set1.setCircleColor(Color.BLACK);
                        set1.setLineWidth(1f);//设置线宽
                        set1.setCircleRadius(3f);//设置焦点圆心的大小
                        set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
                        set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
                        set1.setHighlightEnabled(true);//是否禁用点击高亮线
                        set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
                        set1.setValueTextSize(9f);//设置显示值的文字大小
                        set1.setDrawFilled(false);//设置禁用范围背景填充

                        //格式化显示数据
                        final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
                        set1.setValueFormatter(new IValueFormatter() {
                            @Override
                            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                                return mFormat.format(value);
                            }
                        });
                        if (Utils.getSDKInt() >= 18) {
                            // fill drawable only supported on api level 18 and above
                            Drawable drawable = ContextCompat.getDrawable(homepage2.this, R.color.colorred);
                            set1.setFillDrawable(drawable);//设置范围背景填充
                        } else {
                            set1.setFillColor(Color.BLACK);
                        }
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1);
                        LineData data = new LineData(dataSets);
                        lineChart.setData(data);
                        lineChart.invalidate();
                    }

                    final EditText edit_height = (EditText) findViewById(R.id.edit_height);
                    final EditText edit_weight = (EditText) findViewById(R.id.edit_weight);
                    final TextView count_bmi = (TextView) findViewById(R.id.count_bmi);
                    Button count = (Button) findViewById(R.id.count);
                    count.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            float height = Float.parseFloat(edit_height.getEditableText().toString().trim());
                            float weight = Float.parseFloat(edit_weight.getEditableText().toString().trim());
                            bmi = weight/(height*height);
                            count_bmi.setText("您的身体质量指数为"+bmi);
                            add_weight = Integer.parseInt(edit_weight.getEditableText().toString().trim());
                        }
                    });

                    Button save = (Button) findViewById(R.id.save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            float weight = Float.parseFloat(edit_weight.getEditableText().toString().trim());
                            add_weight = Integer.parseInt(edit_weight.getEditableText().toString().trim());
                            final ArrayList<Entry> values2 = new ArrayList<>();
                            values2.add(new Entry(1,45));
                            values2.add(new Entry(2,46));
                            values2.add(new Entry(3,47));
                            values2.add(new Entry(4,47));
                            values2.add(new Entry(5,46));
                            values2.add(new Entry(6,45));
                            values2.add(new Entry(7,45));
                            values2.add(new Entry(8,46));
                            values2.add(new Entry(9,45));
                            values2.add(new Entry(10,add_weight));
                            LineDataSet set2=(LineDataSet) lineChart.getData().getDataSetByIndex(0);;
                            set2.setValues(values2);
                            lineChart.getData().notifyDataChanged();
                            lineChart.notifyDataSetChanged();
                        }
                    });

                    Button science = (Button) findViewById(R.id.science);
                    science.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent_s = new Intent(homepage2.this,science.class);
                            startActivity(intent_s);
                        }
                    });

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
        Button science = (Button) findViewById(R.id.science);
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_s = new Intent(homepage2.this,science.class);
                startActivity(intent_s);
            }
        });

    }


}
