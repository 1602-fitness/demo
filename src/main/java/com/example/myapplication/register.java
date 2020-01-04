package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.database.sqlite.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.content.ContentValues;

import java.util.Timer;
import java.util.TimerTask;


public class register extends AppCompatActivity {

    private myHelper helper;
    String sex="";
    private static final long DELAY = 1500;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new myHelper(register.this);
        helper.getWritableDatabase();
        helper.close();

        Button btn_register = (Button) findViewById(R.id.register_button);
        final EditText e_account = (EditText) findViewById(R.id.edit_account);
        final EditText e_psw1 = (EditText) findViewById(R.id.edit_psw);
        final EditText e_psw2 = (EditText) findViewById(R.id.edit_psw2);
        final RadioGroup sex_group = (RadioGroup) findViewById(R.id.sex_group);
        final RadioButton sex_man = (RadioButton) findViewById(R.id.sex_man);
        final RadioButton sex_woman = (RadioButton) findViewById(R.id.sex_woman);

        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.sex_man:
                        sex = sex_man.getText().toString();
                        break;
                    case R.id.sex_woman:
                        sex = sex_woman.getText().toString();
                        break;
                    default:break;
                }
//                Toast.makeText(register.this, sex,Toast.LENGTH_SHORT).show();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = e_account.getText().toString();
                String psw1 = e_psw1.getText().toString();
                String psw2 = e_psw2.getText().toString();

//                Toast.makeText(register.this, account+" "+psw1+" "+psw2, Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(register.this,"账户不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw1)||TextUtils.isEmpty(psw2)){
                    Toast.makeText(register.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!psw1.equals(psw2))
                {
                    Toast.makeText(register.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                }else
                {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("account",account);
                    values.put("psw",psw1);
                    values.put("sex",sex);
                    long id = db.insert("users",null,values);
                    Toast.makeText(register.this,"欢迎您："+account+sex+",成为我们的第"+id+"位用户，即将为您跳转",Toast.LENGTH_SHORT).show();

                    final Intent intent = new Intent(register.this,login.class);
                    intent.putExtra("account",account);
                    intent.putExtra("psw",psw1);
                    Timer timer = new Timer();
                    TimerTask tast = new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    };
                    timer.schedule(tast,DELAY);

                }
            }
        });


//        Toast.makeText(register.this, sex,Toast.LENGTH_SHORT).show();
    }
}
