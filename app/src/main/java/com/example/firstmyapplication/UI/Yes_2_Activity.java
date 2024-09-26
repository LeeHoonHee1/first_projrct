package com.example.firstmyapplication.UI;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Yes_2_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_health_yes_2);

        ImageView imageOpenLink = findViewById(R.id.image_open_link_1);
        imageOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 링크를 열기 위한 인텐트 생성
                String url = "https://www.myprotein.co.kr/sports-nutrition/impact-whey-isolate/10530911.html";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
