package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class choose extends AppCompatActivity {
    private List<food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        initfoods();
        foodAdapter adapter = new foodAdapter(choose.this,R.layout.activity_choose_item,foodList);
        ListView listView = (ListView) findViewById(R.id.list_v);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                food f = foodList.get(position);

                Intent intent = new Intent();
                ArrayList<String> data = new ArrayList<String>();
                String name = (String) f.getName();
                data.add(name);
                String energy = "";
                energy = energy+f.getEnergy();
                data.add(energy);
                Toast.makeText(choose.this, name+"含有"+energy+"千卡的能量", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra("data_return",data);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    private void initfoods(){
        food mifan = new food("米饭", 126);
        foodList.add(mifan);
        food youtiao = new food("油条", 386);
        foodList.add(youtiao);
        food mianbao = new food("面包", 130);
        foodList.add(mianbao);
        food xigua = new food("西瓜", 34);
        foodList.add(xigua);
        food zhurou = new food("肥猪肉", 820);
        foodList.add(zhurou);
        food niurou = new food("牛肉", 125);
        foodList.add(niurou);
        food mangguo = new food("芒果", 32);
        foodList.add(mangguo);
        food qiaokeli = new food("巧克力", 586);
        foodList.add(qiaokeli);
        food bingqilin = new food("冰淇淋", 200);
        foodList.add(bingqilin);
        food jirou = new food("鸡肉", 166);
        foodList.add(jirou);
        food sijidou = new food("四季豆", 30);
        foodList.add(sijidou);
        food niuroumian = new food("牛肉面", 540);
        foodList.add(niuroumian);
        food huasheng = new food("花生", 580);
        foodList.add(huasheng);
        food xiehuang = new food("蟹黄", 660);
        foodList.add(xiehuang);
        food fangbianmian = new food("方便面", 470);
        foodList.add(fangbianmian);
    }
}
