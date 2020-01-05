package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class foodAdapter extends ArrayAdapter<food> {
    private int resourceID;
    public foodAdapter(Context context, int textViewResourceID, List<food> objects){
        super(context, textViewResourceID, objects);
        resourceID = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        food f = getItem(position);
        View view;
        class ViewHolder{
            TextView fname;
        }
        ViewHolder viewHolder;
        if (convertView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fname = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.fname.setText(f.getName());
        return view;
    }
}
