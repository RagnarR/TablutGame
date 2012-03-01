package com.valhalla.tablut;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        String[] values = {"New Game", "Settings", "Rules", "About"};

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);
        Map<String, Object> map;
        for (String value : values) {
            map = new HashMap<String, Object>();
            map.put(ATTRIBUTE_NAME_TEXT, value);
            data.add(map);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT};
        int[] to = {R.id.menu_item};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(simpleAdapter);
        Log.d("MenuActivity", "=======================");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("MenuActivity", "We are in OnItemClick method!");
                Log.d("MenuActivity", "Click on ITEM_ID: " + position);

                switch (position) {
                    case 0:
                        Log.d("MenuActivity", "Click on New Game");
                        break;
                    case 1:
                        Log.d("MenuActivity", "Click on Settings");
                        break;
                    case 2:
                        Log.d("MenuActivity", "Click on Rules");
                        break;
                    case 3:
                        Log.d("MenuActivity", "Click on About");
                        break;
                }
            }
        });
    }

    final String ATTRIBUTE_NAME_TEXT = "text";

    ListView listView;
}