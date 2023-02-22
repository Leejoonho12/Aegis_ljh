package com.example.myapplication1213;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticeAdapter extends BaseAdapter {

    public ArrayList<NoticeVO> listViewItemList = new ArrayList<NoticeVO>();
    public NoticeAdapter(){

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
            convertView = inflater.inflate(R.layout.row, parent, false);
        }

        //추가수정시 건들부분
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv_row_title);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv_row_content);
        TextView tv3 = (TextView) convertView.findViewById(R.id.tv_row_writer);
        TextView tv4 = (TextView) convertView.findViewById(R.id.tv_row_day);

        NoticeVO listViewItem = listViewItemList.get(position);

        tv1.setText(listViewItem.getTitle());
        tv2.setText(listViewItem.getContent());
        tv3.setText(listViewItem.getWriter());
        tv4.setText(listViewItem.getDay());
        //여기까지

        return convertView;
    }

    public void addItem(String title, String content, String writer, String day){
        NoticeVO item = new NoticeVO();

        item.setTitle(title);
        item.setContent(content);
        item.setWriter(writer);
        item.setDay(day);

        listViewItemList.add(item);
    }

}
