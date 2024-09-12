package com.example.firstmyapplication.UI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.MainActivity;
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

                // 입력 값이 비어 있는지 확인
                if (TextUtils.isEmpty(title)) {
                    titleEditText.setError("Title is required");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    contentEditText.setError("Content is required");
                    return;
                }

                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("content", content);
                database.insert("posts", null, values);

                // MainActivity로 이동
                Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 데이터베이스 연결 종료
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
