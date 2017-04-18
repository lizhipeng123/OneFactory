package com.i5tong.epubreaderlib.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.i5tong.epubreaderlib.bean.RowData;

import java.util.List;

/**
 * Created by 王霖 on 2015/4/24 0024.
 */
public class CharacterAdapter extends ArrayAdapter<RowData> {
    private LayoutInflater inflater;
    private List<RowData> objects;

    public CharacterAdapter(Context context, List<RowData> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    private class ViewHolder {
        TextView text = null;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            holder.text = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RowData rowData = getItem(position);
        holder.text.setText(rowData.getTitle());
        return convertView;
    }

}