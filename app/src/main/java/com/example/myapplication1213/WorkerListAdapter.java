package com.example.myapplication1213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkerListAdapter extends BaseAdapter {

    public ArrayList<workerListVO> listViewItemList = new ArrayList<workerListVO>();
    public WorkerListAdapter(){

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
            convertView = inflater.inflate(R.layout.activity_workeritem, parent, false);
        }

        //추가수정시 건들부분
        TextView tv1 = (TextView) convertView.findViewById(R.id.item_name);
        TextView tv2 = (TextView) convertView.findViewById(R.id.item_id);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        EditText edt1 = convertView.findViewById(R.id.edt_item_beacon);

        workerListVO listViewItem = listViewItemList.get(position);

        tv1.setText(listViewItem.getName());
        tv2.setText(listViewItem.getId());
        checkBox.setText("");
        edt1.setText("");

        return convertView;
    }

    public void addItem(String name, String id){
        workerListVO item = new workerListVO();

        item.setName(name);
        item.setId(id);

        listViewItemList.add(item);
    }

}
