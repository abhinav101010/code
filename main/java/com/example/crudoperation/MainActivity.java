package com.example.crudoperation;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    Data data;
    public static ArrayList<Data> dataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent add = new Intent(this, add_data.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(add);
            }
        });

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this, dataArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"View", "Edit", "Delete"};
                builder.setTitle(dataArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                startActivity(new Intent(getApplicationContext(), DetailActivity.class).putExtra("position", position));
                                break;

                            case 1:
                                startActivity(new Intent(getApplicationContext(), edit_activity.class).putExtra("position", position));
                                break;

                            case 2:
                                deleteData(dataArrayList.get(position).getId());
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });

        retriveData();
    }

    public void deleteData(final String id){
//        ProgressDialog progressDialog = new ProgressDialog();
        StringRequest request = new StringRequest(Request.Method.POST, "http://api.m2msim.in/api/delete.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MyApp",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyApp", "Something Went Wrong");
                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id",id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        retriveData();
    }

    public void retriveData(){
        StringRequest request = new StringRequest(Request.Method.POST, "http://api.m2msim.in/api/data.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dataArrayList.clear();
                try {
                    Log.d("MyApp", response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("MyApp", String.valueOf(jsonArray.length()));

                    for (int i = 0; i<=jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String email = object.getString("email");
                        String phone = object.getString("phone");

                        data = new Data(id,name,email,phone);

                        dataArrayList.add(data);
                        adapter.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
