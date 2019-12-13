package com.example.asus.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adaptermatakuliah extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<matakuliah> item;

    public adaptermatakuliah(Activity activity, List<matakuliah> item) {
        this.activity = activity;
        this.item = item;
    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater==null){
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view==null){
            view=inflater.inflate(R.layout.layout,null);
        }
        TextView nama=view.findViewById(R.id.view1);

        matakuliah mata;

        mata=item.get(i);
        nama.setText(mata.getNama());
        return view;
    }
}
