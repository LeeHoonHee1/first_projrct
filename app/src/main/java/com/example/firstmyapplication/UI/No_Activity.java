package com.example.firstmyapplication.UI;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;

public class No_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_health_no);  // activity_yes 레이아웃을 사용한다고 가정
    }
}