package com.floydmobile.uicatalog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SimpleListActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_tables);
        ListView vList = (ListView)this.findViewById(R.id.list_tables);
        String[] values = new String[] { "List Item Style 1", "List Item Syle 2","List With Different Styles"};
        MyCustomAdapter mAdapter;
        mAdapter = new MyCustomAdapter();
        for (String value : values) {
            mAdapter.addItem(value);
        }
        for (int i = mAdapter.getCount()-1; i < 50; i++)
            mAdapter.addItem("Table Style " + i);
        vList.setAdapter(mAdapter);

        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent;
                    intent = new Intent(SimpleListActivity.this, ListItemStyle1Activity.class);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent;
                    intent = new Intent(SimpleListActivity.this, ListItemStyle2Activity.class);
                    startActivity(intent);
                }
                if (position == 2){
                    Intent intent;
                    intent = new Intent(SimpleListActivity.this, ActivityListItem.class);
                    startActivity(intent);
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_list, menu);
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
        private ArrayList mData = new ArrayList();
        private LayoutInflater mInflater;

        public MyCustomAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final String item){
            mData.add(item);
            notifyDataSetChanged();
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
            System.out.println("getView " + position + " " + convertView);
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.simple_table_list_item,null);
                holder = new ViewHolder();
                holder.textView = (TextView)convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText((String)mData.get(position));
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
