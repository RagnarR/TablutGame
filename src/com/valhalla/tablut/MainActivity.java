package com.valhalla.tablut;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: aaniskou
 * Date: 29.02.12
 * Time: 5:43
 * To change this template use File | Settings | File Templates.
 */
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
    }

    final String ATTRIBUTE_NAME_TEXT = "text";

    ListView listView;
}