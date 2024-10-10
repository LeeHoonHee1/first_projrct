package com.example.firstmyapplication.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;

public class No_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_health_no);

        ImageView imageOpenLink = findViewById(R.id.image_open_link2);
        imageOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 링크를 열기 위한 인텐트 생성
                String url = "https://www.coupang.com/vp/products/6422282333?itemId=13823103047&vendorItemId=81073326157&q=evl+%ED%94%84%EB%A1%9C%ED%8B%B4&itemsCount=36&searchId=f1f01f5c5c6744a29b4fb7b27ee418cf&rank=0&isAddedCart=";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageView imageOpenLink2 = findViewById(R.id.image_open_link3);
        imageOpenLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.coupang.com/vp/products/7424965899?vendorItemId=86386493912&sourceType=SDP_ALSO_VIEWED&searchId=0986610726e145ca9ee6b29aa8263894&rmdId=0986610726e145ca9ee6b29aa8263894&eventLabel=recommendation_widget_pc_sdp_001&platform=web&rmdABTestInfo=69385:A&rmdValue=p6422282333:vt-1.0.0:p7424965899&isAddedCart=";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageView imageOpenLink3 = findViewById(R.id.image_open_link4);
        imageOpenLink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.coupang.com/vp/products/6731349444?itemId=17352300145&vendorItemId=4041827021&q=%EC%BB%B4%EB%B2%B3&itemsCount=36&searchId=dd8f0b7fc8f14cb4bd5dfd3e5bf21643&rank=0&isAddedCart=";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageView imageOpenLink4 = findViewById(R.id.image_open_link5);
        imageOpenLink4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.coupang.com/vp/products/5463938851?itemId=9240868&vendorItemId=3273079417&q=%EC%98%B4%ED%8B%B0%EB%A9%88+%EB%89%B4%ED%8A%B8%EB%A6%AC%EC%A7%84+%EA%B3%A8%EB%93%9C+%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C%EC%9B%A8%EC%9D%B4&itemsCount=36&searchId=649b5826379b45eaaef24e4eafd9b44d&rank=0&isAddedCart=";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageView imageOpenLink5 = findViewById(R.id.image_open_link6);
        imageOpenLink5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.coupang.com/vp/products/8203767283?itemId=17461238041&vendorItemId=3180840758&q=%EC%8B%A0%ED%83%806&itemsCount=36&searchId=8a8c7a460be84719bc8de73defc265aa&rank=1&isAddedCart=";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ImageView imageOpenLink6 = findViewById(R.id.image_open_link7);
        imageOpenLink6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.coupang.com/vp/products/6833737332";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}