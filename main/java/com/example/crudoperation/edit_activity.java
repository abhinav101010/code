package com.example.crudoperation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

public class edit_activity extends AppCompatActivity {


    String txtname, txtemail, txtphone;
    TextView name, email, phone;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        name.setText(MainActivity.dataArrayList.get(position).getName());
        email.setText(MainActivity.dataArrayList.get(position).getEmail());
        phone.setText(MainActivity.dataArrayList.get(position).getPhone());
    }

    public void edit(View view){
        txtname = String.valueOf(name.getText());
        txtemail = String.valueOf(email.getText());
        txtphone = String.valueOf(phone.getText());

        StringRequest request = new StringRequest(Request.Method.POST, "http://api.m2msim.in/api/edit.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("done")){
                    Toast.makeText(edit_activity.this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(edit_activity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                params.put("id",MainActivity.dataArrayList.get(position).getId());
                params.put("name",txtname);
                params.put("email",txtemail);
                params.put("phone",txtphone);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
