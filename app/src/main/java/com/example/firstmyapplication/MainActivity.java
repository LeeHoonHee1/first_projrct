package com.example.firstmyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.firstmyapplication.Login.Login_Activity;
import com.example.firstmyapplication.UI.Health;
import com.example.firstmyapplication.UI.Share;
import com.example.firstmyapplication.UI.Star1;
import com.example.firstmyapplication.UI.Star2;
import com.example.firstmyapplication.UI.Star3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private void performCommonTask() {  // 공통 작업 수행
    }
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Login_Activity login_activity; // 로그인 프래그먼트
    private Star1 Star1;
    private Star2 Star2;
    private Star3 Star3;
    private Share Share;
    private Health Health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_star1:
                        setFrag(0);
                        break;
                    case R.id.navigation_star2:
                        setFrag(1);
                        break;
                    case R.id.navigation_star3:
                        setFrag(2);
                        break;
                    case R.id.navigation_share:
                        setFrag(3);
                        break;
                    case R.id.navigation_health:
                        setFrag(4);
                        break;

                }
                return true;
            }
        });
        login_activity= new Login_Activity();
        Star1 = new Star1();
        Star2 = new Star2();
        Star3 = new Star3();
        Share = new Share();
        Health = new Health();

        setLogin_activity();  //첫 프레그먼트 화면을 무엇으로 지정해 줄 것인지 선택.

    }
    private void setLogin_activity() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, login_activity)
                .commit();
    }

    public void onLoginSuccess() {
        setFrag(0);

        // 바텀 네비게이션 다시 표시
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavi);
        if (bottomNav != null) {
            bottomNav.setVisibility(View.VISIBLE);
        }
    }

    private  void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        if (n == 0) {
            ft.replace(R.id.main_frame, Star1);
            ft.commit();
        } else if (n == 1) {
            ft.replace(R.id.main_frame, Star2);
            ft.commit();
        } else if (n == 2) {
            ft.replace(R.id.main_frame, Star3);
            ft.commit();
        } else if (n == 3) {
            ft.replace(R.id.main_frame, Share);
            ft.commit();
        } else if (n == 4) {
            ft.replace(R.id.main_frame, Health);
            ft.commit();
        }
    }
}
