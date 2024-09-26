package com.example.firstmyapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;

public class Yes_Activity extends AppCompatActivity {

    Button yesButton1, noButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_health_yes);

        // 버튼 연결
        yesButton1 = findViewById(R.id.yesButton1);
        noButton1 = findViewById(R.id.noButton1);

        // Option 1 버튼 클릭 시 Option1_Activity로 이동
        yesButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yes_Activity.this, Yes_1_Activity.class);
                startActivity(intent);
            }
        });

        // Option 2 버튼 클릭 시 Option2_Activity로 이동
        noButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yes_Activity.this, Yes_2_Activity.class);
                startActivity(intent);
            }
        });
    }
}