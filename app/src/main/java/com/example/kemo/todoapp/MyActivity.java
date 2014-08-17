package com.example.kemo.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    private ArrayList<String> todoItems;
    private ArrayAdapter<String> todoAdapter;
    private ListView lvItems;
    private EditText etNewItem;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        // attach the adapter to listview itself
        lvItems.setAdapter(todoAdapter);
        setupListViewListener();
        setupListViewOnClickListener();
    }

    // ActivityOne.java
    public void launchComposeView(String editItemText, String editItemPosition) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MyActivity.this, EditItemActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("editItem", editItemText);
        i.putExtra("position", editItemPosition);
        // brings up the second activity
        startActivityForResult(i,20);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String editItem = data.getStringExtra("editItem");
            String itemPosition = data.getStringExtra("editItemPosition");
            int position = Integer.valueOf(itemPosition);
            // Toast the name to display temporarily on screen
            todoItems.set(position,editItem);
            todoAdapter.notifyDataSetChanged();
//            todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
//            // attach the adapter to listview itself
//            lvItems.setAdapter(todoAdapter);
//            setupListViewListener();
//            setupListViewOnClickListener();
        }
    }

    private void readItems() {
        File file = getFilesDir();
        File todoFile = new File(file, "todo.txt");

        try {
            //final List<String> lines = Files.readLines(file, Charset.defaultCharset());
            //todoItems = new ArrayList<String>(Files.readLines(file, Charset.defaultCharset()));
            List<String> loadItems = FileUtils.readLines(todoFile);

            todoItems = new ArrayList<String>(loadItems);
        } catch (IOException e) {
            todoItems = new ArrayList<String>();
            e.printStackTrace();
        }
    }

    private void saveItems() {
        File file = getFilesDir();
        File todoFile = new File(file, "todo.txt");
        try {
            //Files.asCharSink(file, Charsets.UTF_8).writeLines(todoItems);
            FileUtils.writeLines(todoFile,todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setupListViewOnClickListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 launchComposeView(todoItems.get(position), String.valueOf(position));
            }
        });
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                todoItems.remove(pos);
                todoAdapter.notifyDataSetChanged();
                saveItems();
                return true;
            }
        });
    }

    private void populateArrayItems() {
        todoItems = new ArrayList<String>();
        todoItems.add("Item 1");
        todoItems.add("Item 2");
        todoItems.add("item 3");
    }

    public void onAddedItem(View v) {
        String itemText = etNewItem.getText().toString();
        todoAdapter.add(itemText);
        etNewItem.setText("");
        saveItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
