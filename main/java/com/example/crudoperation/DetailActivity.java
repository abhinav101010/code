package com.example.crudoperation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView txtid, txtname, txtemail, txtphone;
    int position;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        txtid = findViewById(R.id.txtid);
        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        txtphone = findViewById(R.id.txtphone);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        txtid.setText("ID : "+MainActivity.dataArrayList.get(position).getId());
        txtname.setText("Name : "+MainActivity.dataArrayList.get(position).getName());
        txtemail.setText("Email : "+MainActivity.dataArrayList.get(position).getEmail());
        txtphone.setText("Phone : "+MainActivity.dataArrayList.get(position).getPhone());
    }
}
