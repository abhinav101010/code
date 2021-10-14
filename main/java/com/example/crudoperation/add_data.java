package com.example.crudoperation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class add_data extends AppCompatActivity {
    EditText name, email, phone;
    String txtName, txtEmail, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

    }

    public void submit(View view){
        txtName = String.valueOf(name.getText());
        txtEmail = String.valueOf(email.getText());
        txtPhone = String.valueOf(phone.getText());
        Log.d("MyApp", txtName);
        Log.d("MyApp", txtEmail);
        Log.d("MyApp", txtPhone);

        StringRequest request = new StringRequest(Request.Method.POST, "http://api.m2msim.in/api/insert_data.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("inserted")){
                    Toast.makeText(add_data.this, "Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(add_data.this, response+" : ELSE", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "Something Went Wrong");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name",txtName);
                params.put("email",txtEmail);
                params.put("phone",txtPhone);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
