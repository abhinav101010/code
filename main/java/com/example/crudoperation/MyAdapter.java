package com.example.crudoperation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Data>{
    Context context;
    List<Data> arrayListData;

    public MyAdapter(@NonNull Context context, List<Data> arrayListData) {
        super(context, R.layout.custom_list_item, arrayListData);

        this.context = context;
        this.arrayListData = arrayListData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);
        TextView tvEmail = view.findViewById(R.id.txt_email);

        tvID.setText(arrayListData.get(position).getId());
        tvName.setText(arrayListData.get(position).getName());
        tvEmail.setText(arrayListData.get(position).getEmail());

        return view;
    }
}
