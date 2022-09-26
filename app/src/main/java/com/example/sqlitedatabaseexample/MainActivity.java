package com.example.sqlitedatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private DataBase dataBase;
    private EditText et_door,et_place,et_country,et_username,et_email,et_phone,et_id;
    Button bt_savedat,bt_display;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this);

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_Phone);
        et_door = findViewById(R.id.et_door);
        et_place =findViewById(R.id.et_place);
        et_country = findViewById(R.id.et_country);

        bt_savedat = findViewById(R.id.bt_savedat);
        bt_display = findViewById(R.id.bt_display);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bt_savedat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_username.getText().toString();
                String email = et_email.getText().toString();
                String phone = et_phone.getText().toString();
                String door = et_door.getText().toString();
                String place = et_place.getText().toString();
                String country = et_place.getText().toString();

                dataBase.insertContact(name,phone,email,door,place,country);
                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
            }
        });
        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor rs = dataBase.getData(1);
                Toast.makeText(MainActivity.this,"Data"+rs, Toast.LENGTH_SHORT).show();
            }
        });
}}