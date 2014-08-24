package com.example.kemo.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.kemo.todoapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditItemActivity extends Activity {

    private ToDoItem intentEditItem;
    private String editItemPosition;
    private EditText etItem;
    private EditText etDate;
    private EditText etPriority;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        //intentEditItem = intent.getStringExtra("editItem");
        intentEditItem = (ToDoItem)intent.getSerializableExtra("ToDoItem");
        editItemPosition = intent.getStringExtra("position");
        etItem = (EditText) findViewById(R.id.etItem);
        etDate = (EditText) findViewById(R.id.etDate);
        etPriority = (EditText) findViewById(R.id.etPriority);
        if (null != intentEditItem.getDescription()) {
            etItem.setText(intentEditItem.getDescription());
        }
        etPriority.setText(String.valueOf(intentEditItem.getPriority()));
        if (null != intentEditItem.getDueDate()) {
            SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
            String date = sdf.format(intentEditItem.getDueDate());
            etDate.setText(date);
        } else {
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
            String date = sdf.format(today);
            etDate.setText(date);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_item, menu);
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

    public void onSubmit(View view) {
        Intent intent = new Intent();
        ToDoItem toDoItem = new ToDoItem(Integer.valueOf(etPriority.getText().toString()),new Date(etDate.getText().toString()), etItem.getText().toString());
        intent.putExtra("ToDoItem",toDoItem);
        intent.putExtra("editItemPosition", editItemPosition);
        setResult(RESULT_OK, intent);
        finish();

    }
}
