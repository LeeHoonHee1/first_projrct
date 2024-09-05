package com.example.firstmyapplication.UI;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;

public class AddPostActivity extends AppCompatActivity {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        final EditText titleEditText = findViewById(R.id.titleEditText);
        final EditText contentEditText = findViewById(R.id.contentEditText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("content", content);
                database.insert("posts", null, values);

                Intent intent = new Intent(AddPostActivity.this, Share.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

