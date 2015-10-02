package com.floydmobile.uicatalog;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class ListItemStyle2Activity extends ActionBarActivity {

    final ArrayList<String> list = new ArrayList<>();
    final ArrayList<String> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_style2);
        String[] nameArr = new String[] {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta",
                "Eta", "Theta", "Iota", "Kappa", "Lambda","Mu", "Nu", "Xi","Omicron","Pi", "Rho",
                "Sigma", "Tau", "Upsilon", "Phi", "Chi", "Psi", "Omega"};
        final String[] symbolArr = new String[] {"\u0391", "\u0392", "\u0393", "\u0394", "\u0395", "\u0396",
                "\u0397", "\u0398", "\u0399", "\u039A", "\u039B","\u039C", "\u039D", "\u039E","\u039F","\u03A0",
                "\u03A1", "\u03A3", "\u03A4", "\u03A5", "\u03A6", "\u03A7", "\u03A8", "\u03A9"};

        Collections.addAll(list, nameArr);
        Collections.addAll(list2, symbolArr);

        ListView listView = (ListView) findViewById(R.id.listView2);
        MyCustomAdapter mAdapter;
        mAdapter = new MyCustomAdapter();
        for (int i = 0; i < list.size(); i++){
            mAdapter.addItem(list.get(i));
        }
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //final String item = (String) parent.getItemAtPosition(position);
                final String item = (String) symbolArr[position];
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_item_style2, menu);
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
                convertView = mInflater.inflate(android.R.layout.simple_list_item_2,null);
                holder = new ViewHolder();
                holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView1.setText( list.get(position));
            holder.textView2.setText( list2.get(position));
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
    }
}
