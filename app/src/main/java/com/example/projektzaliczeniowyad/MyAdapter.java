package com.example.projektzaliczeniowyad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    Context context;
    int [] pcty;
    String [] opisy;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView textView;

    public MyAdapter(Context context, int [] pcty, String [] opisy) {
        super();
        this.context = context;
        this.pcty = pcty;
        this.opisy = opisy;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pcty.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.my_spinner_items, null);
        imageView = convertView.findViewById(R.id.imageView);
        textView = convertView.findViewById(R.id.textView);
        imageView.setImageResource(pcty[position]);
        textView.setText(opisy[position]);
        return convertView;
    }
}
