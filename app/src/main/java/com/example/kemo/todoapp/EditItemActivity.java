package com.example.kemo.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.kemo.todoapp.R;

public class EditItemActivity extends Activity {

    private String intentEditItem;
    private String editItemPosition;
    private EditText etItem;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();
        intentEditItem = intent.getStringExtra("editItem");
        editItemPosition = intent.getStringExtra("position");
        etItem = (EditText) findViewById(R.id.etItem);
        etItem.setText(intentEditItem);
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
        intent.putExtra("editItem", etItem.getText().toString()); // pass arbitrary data to launched activity
        intent.putExtra("editItemPosition", editItemPosition);
        setResult(RESULT_OK, intent);
        //startActivityForResult(intent, REQUEST_CODE);
        finish();
    }
}
