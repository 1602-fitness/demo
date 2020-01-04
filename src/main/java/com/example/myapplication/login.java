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
    protected Button btn_login;
    protected Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_register = (Button) findViewById(R.id.register_button);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);

            }
        });

        EditText edit_account = (EditText) findViewById(R.id.edit_account);
        EditText edit_psw = (EditText) findViewById(R.id.edit_psw);

        Intent intent1 = getIntent();
        String account = intent1.getStringExtra("account");
        String psw = intent1.getStringExtra("psw");

        edit_account.setText(account);
        edit_psw.setText(psw);

    }
}
