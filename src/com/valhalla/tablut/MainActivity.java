package com.valhalla.tablut;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        String[] values = {"New Game", "Settings", "Rules", "About"};

//        ListView listView = (ListView) findViewById(R.id.list);


// First paramenter - Context
// Second parameter - Layout for the row
// Third parameter - ID of the View to which the data is written
// Forth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

// Assign adapter to ListView
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("MenuActivity", "We are in OnItemClick method!");
        Log.d("MenuActivity", "Click on ITEM_ID: " + position);

        switch (position) {
            case 0:
                startActivity(new Intent(this, GameActivity.class));
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

    final String ATTRIBUTE_NAME_TEXT = "text";

    ListView listView;
}