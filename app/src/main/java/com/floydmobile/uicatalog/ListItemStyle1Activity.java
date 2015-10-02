package com.floydmobile.uicatalog;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class ListItemStyle1Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_style1);
        String[] nameArr = new String[] {"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta",
                "Eta", "Theta", "Iota", "Kappa", "Lambda","Mu", "Nu", "Xi","Omicron","Pi", "Rho",
        "Sigma", "Tau", "Upsilon", "Phi", "Chi", "Psi", "Omega"};
        final String[] symbolArr = new String[] {"\u0391", "\u0392", "\u0393", "\u0394", "\u0395", "\u0396",
                "\u0397", "\u0398", "\u0399", "\u039A", "\u039B","\u039C", "\u039D", "\u039E","\u039F","\u03A0",
                "\u03A1", "\u03A3", "\u03A4", "\u03A5", "\u03A6", "\u03A7", "\u03A8", "\u03A9"};
        final ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, nameArr);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,nameArr);
        listView.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.menu_list_item_style1, menu);
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
}
