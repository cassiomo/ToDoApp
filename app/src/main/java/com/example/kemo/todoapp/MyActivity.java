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

import com.google.common.base.Splitter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class MyActivity extends Activity {

    private ArrayList<ToDoItem> todoItems;
    private ArrayAdapter<ToDoItem> todoAdapter;
    private ListView lvItems;
    private EditText etNewItem;
    private final int REQUEST_CODE = 20;
    private final static String fileName = "myToDoApp5.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        todoAdapter = new ArrayAdapter<ToDoItem>(this, android.R.layout.simple_list_item_1, todoItems);
        // attach the adapter to listview itself
        lvItems.setAdapter(todoAdapter);
        setupListViewListener();
        setupListViewOnClickListener();
    }

    public void launchComposeView(ToDoItem toDoItem, String editItemPosition) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MyActivity.this, EditItemActivity.class);
        i.putExtra("ToDoItem", toDoItem);
        i.putExtra("position", editItemPosition);
        // brings up the second activity
        startActivityForResult(i,20);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            ToDoItem editItem = (ToDoItem) data.getSerializableExtra("ToDoItem");
            String itemPosition = data.getStringExtra("editItemPosition");
            int position = Integer.valueOf(itemPosition);
            // Toast the name to display temporarily on screen
            todoItems.set(position,editItem);
            //Collections.sort(todoItems);
            todoAdapter.notifyDataSetChanged();
        }
    }

    private void readItems() {
        File file = getFilesDir();
        File todoFile = new File(file, fileName);
        todoItems = new ArrayList<ToDoItem>();

        try {
            //final List<String> lines = Files.readLines(file, Charset.defaultCharset());
            //todoItems = new ArrayList<String>(Files.readLines(file, Charset.defaultCharset()));
            List<String> loadItems = FileUtils.readLines(todoFile);
            for (String item : loadItems) {
                //Iterable<String> result = Splitter.on(',').split(item);
                String [] tokens = item.split(" ");
                ToDoItem toDoItem = new ToDoItem();
                if (null != tokens[0]) {
                    toDoItem.setDueDate(new Date(tokens[0]));
                }
                if (null != tokens[1]) {
                    toDoItem.setDescription(tokens[1]);
                }
                if (null != tokens[2]) {
                    toDoItem.setPriority(Integer.valueOf(tokens[2]));
                }
                todoItems.add(toDoItem);
            }
            //List<ToDoItem> loadItems = FileUtils.readLines(todoFile);

            //todoItems = new ArrayList<ToDoItem>(loadItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveItems() {
        // save to file
        File file = getFilesDir();
        File todoFile = new File(file, fileName);
        try {
            //Files.asCharSink(file, Charsets.UTF_8).writeLines(todoItems);
            FileUtils.writeLines(todoFile,todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save to database
        for (ToDoItem toDoItem : todoItems) {
            ToDoItemModel toDoItemModel = new ToDoItemModel();
            toDoItemModel.id = UUID.randomUUID().toString();
            toDoItemModel.priority = toDoItem.getPriority();
            toDoItemModel.dueDate = toDoItem.getDueDate();
            toDoItemModel.description = toDoItem.getDescription();
            toDoItemModel.save();
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

    public void onAddedItem(View v) {
        String itemText = etNewItem.getText().toString();
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setDescription(itemText);
        toDoItem.setDueDate(new Date());
        toDoItem.setPriority(0);
        todoAdapter.add(toDoItem);
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
