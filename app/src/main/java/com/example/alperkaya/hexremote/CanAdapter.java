package com.example.alperkaya.hexremote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CanAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<CanMessage> mList;
    private LayoutInflater mLayoutInflater;

    public CanAdapter(Context context, ArrayList<CanMessage> list){
        mContext = context;
        mList = list;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void addItem(CanMessage mCanMsg){
        mList.add(mCanMsg);
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
        TextView txtFifth;
        TextView txtSixth;
        TextView txtSeventh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;

        if(convertView == null){

            convertView = mLayoutInflater.inflate(R.layout.listview_row, parent);
            mViewHolder = new ViewHolder();
            mViewHolder.txtFirst = (TextView) convertView.findViewById(R.id.byteOne);
            mViewHolder.txtSecond = (TextView) convertView.findViewById(R.id.byteTwo);
            mViewHolder.txtThird = (TextView) convertView.findViewById(R.id.byteThree);
            mViewHolder.txtFourth = (TextView) convertView.findViewById(R.id.byteFour);
            mViewHolder.txtFifth = (TextView) convertView.findViewById(R.id.byteFive);
            mViewHolder.txtSixth = (TextView) convertView.findViewById(R.id.byteSix);
            mViewHolder.txtSeventh = (TextView) convertView.findViewById(R.id.byteSeven);

            convertView.setTag(mViewHolder);

        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.txtFirst.setText(mList.get(position).getCanByte(1));
        mViewHolder.txtSecond.setText(mList.get(position).getCanByte(2));
        mViewHolder.txtThird.setText(mList.get(position).getCanByte(3));
        mViewHolder.txtFourth.setText(mList.get(position).getCanByte(4));
        mViewHolder.txtFifth.setText(mList.get(position).getCanByte(5));
        mViewHolder.txtSixth.setText(mList.get(position).getCanByte(6));
        mViewHolder.txtSeventh.setText(mList.get(position).getCanByte(7));


        return convertView;
    }
}
