package com.example.myapplication1213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScannerAdapter extends BaseAdapter {
    public ArrayList<scannerVO> listViewItemList = new ArrayList<scannerVO>();
    public ScannerAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.scannerlist, parent, false);
        }

        //추가수정시 건들부분
        TextView tv1 = (TextView) convertView.findViewById(R.id.textView14);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView15);
        TextView tv3 = (TextView) convertView.findViewById(R.id.textView16);

        scannerVO listViewItem = listViewItemList.get(position);

        tv1.setText(listViewItem.getScanner());
        tv2.setText(listViewItem.getName());
        tv3.setText(listViewItem.getBeacon());
        //여기까지

        return convertView;
    }

    public void addItem(String scanner, String name, String beacon){
        scannerVO item = new scannerVO();

        item.setScanner(scanner);
        item.setName(name);
        item.setBeacon(beacon);

        listViewItemList.add(item);
    }
}
