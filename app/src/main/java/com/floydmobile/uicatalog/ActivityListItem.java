package com.floydmobile.uicatalog;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;


public class ActivityListItem extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list_item);

        ListView listView = (ListView) findViewById(R.id.listView3);
        MyCustomAdapter mAdapter = new MyCustomAdapter();
        for (int i = 1; i<50; i++){
            mAdapter.addItem("item" + i);
            if (i%4 == 0) {
                mAdapter.addSeparatorItem("separator " + i);
            }
            if (i%5 == 0) {
                mAdapter.addSeatRowItem("seatrow " + i);
            }
        }
        listView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_list_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyCustomAdapter extends BaseAdapter {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_SEPERATOR = 1;
        private static final int TYPE_SEATROW = 2;
        private static final int TYPE_MAX_COUNT = 3;

        private ArrayList mData = new ArrayList();
        private LayoutInflater mInflater;

        private TreeSet mSeperatorSet = new TreeSet();
        private TreeSet mSeatRowSet = new TreeSet();

        public MyCustomAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final String item){
            mData.add(item);
            notifyDataSetChanged();
        }

        public void addSeparatorItem(final String item) {
            mData.add(item);
            //save separator position
            mSeperatorSet.add(mData.size() - 1);
            notifyDataSetChanged();
        }

        public void addSeatRowItem(final String item) {
            mData.add(item);
            //save separator position
            mSeatRowSet.add(mData.size() - 1);
            notifyDataSetChanged();
        }
        @Override
        public int getItemViewType(int position){
            //return mSeperatorSet.contains(position) ? TYPE_SEPERATOR : TYPE_ITEM;
            if (mSeperatorSet.contains(position)){return TYPE_SEPERATOR;}
            else if(mSeatRowSet.contains(position)){return TYPE_SEATROW;}
            else {return TYPE_ITEM;}
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position){
            return (String) mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            ViewHolder2 holder2 = null;
            int type = getItemViewType(position);
            System.out.println("getView " + position + " " + convertView);

            if (convertView == null) {
                switch (type) {
                    case TYPE_ITEM:
                        convertView = mInflater.inflate(R.layout.list_item_1,parent,false);
                        holder = new ViewHolder();
                        holder.textView1 = (TextView) convertView.findViewById(R.id.textView6);
                        convertView.setTag(holder);
                        break;
                    case TYPE_SEPERATOR:
                        convertView = mInflater.inflate(R.layout.list_item_2,parent,false);
                        holder = new ViewHolder();
                        holder.textView1 = (TextView) convertView.findViewById(R.id.textView7);
                        convertView.setTag(holder);
                        break;
                    case TYPE_SEATROW:
                        convertView = mInflater.inflate(R.layout.list_item_3,parent,false);
                        holder2 = new ViewHolder2();
                        holder2.imageView1 = (ImageView) convertView.findViewById(R.id.imageView);
                        holder2.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
                        holder2.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);
                        convertView.setTag(holder2);
                        break;
                }

            } else {
                switch (type) {
                    case TYPE_ITEM:
                        holder = (ViewHolder) convertView.getTag();
                        holder.textView1.setText((String) mData.get(position));
                        break;
                    case TYPE_SEPERATOR:
                        holder = (ViewHolder) convertView.getTag();
                        holder.textView1.setText((String) mData.get(position));
                        break;
                    case TYPE_SEATROW:
                        holder2 = (ViewHolder2) convertView.getTag();
                        holder2.imageView1.setImageResource(R.drawable.ic_launcher);
                        break;
                }
            }


            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
    }
    public static class ViewHolder2 {
        public ImageView imageView1;
        public ImageView imageView2;
        public ImageView imageView3;
    }

}
