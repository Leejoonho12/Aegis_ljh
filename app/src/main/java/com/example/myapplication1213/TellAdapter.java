package com.example.myapplication1213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TellAdapter extends BaseAdapter {

    public ArrayList<TellVO> listViewItemList = new ArrayList<TellVO>();
    public TellAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tell_list, parent, false);
        }

        //추가수정시 건들부분
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv_tell_name);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv_tell_num);

        TellVO listViewItem = listViewItemList.get(position);

        tv1.setText(listViewItem.getName());
        tv2.setText(listViewItem.getTnum());
        //여기까지

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    //여기도
    public void addItem(String name, String tnum){
        TellVO item = new TellVO();

        item.setName(name);
        item.setTnum(tnum);

        listViewItemList.add(item);
    }

}
