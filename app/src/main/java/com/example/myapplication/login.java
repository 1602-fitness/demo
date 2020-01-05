package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private myHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText edit_account = (EditText) findViewById(R.id.edit_account);
        final EditText edit_psw = (EditText) findViewById(R.id.edit_psw);

        Button btn_register = (Button) findViewById(R.id.register_button);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);

            }
        });

        Intent intent1 = getIntent();
        String account = intent1.getStringExtra("account");
        String psw = intent1.getStringExtra("psw");

        edit_account.setText(account);
        edit_psw.setText(psw);
        Button btn_login = (Button)findViewById(R.id.login_button);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(login.this,edit_account.getText().toString()+" "+edit_psw.getText().toString(), Toast.LENGTH_SHORT).show();
                helper = new myHelper(login.this);
                helper.getWritableDatabase();
                helper.close();
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor c = db.rawQuery("SELECT psw From users WHERE account=?",new String[]{edit_account.getText().toString()});
                if (c.moveToFirst()){
                    String account = edit_account.getText().toString();
                    String psw = c.getString(c.getColumnIndex("psw"));
                    if (psw.equals(edit_psw.getText().toString())){
//                        Toast.makeText(login.this,"success",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(login.this,homepage2.class);
                        startActivity(intent2);
                    }else{
                        Toast.makeText(login.this,"请输入正确密码",Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(login.this,account+" "+psw,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(login.this,"您还未加入我们，请先注册",Toast.LENGTH_SHORT).show();
                    edit_account.setText("");
                    edit_psw.setText("");
                }
            }
        });
    }
}
