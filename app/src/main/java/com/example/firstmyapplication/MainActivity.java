package com.example.firstmyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstmyapplication.Drawer.JobInfo_Activity;
import com.example.firstmyapplication.Drawer.Profile_Activity;
import com.example.firstmyapplication.Drawer.SchoolInfo_Activity;
import com.example.firstmyapplication.Login.Login_Activity;
import com.example.firstmyapplication.UI.Health;
import com.example.firstmyapplication.UI.Share;
import com.example.firstmyapplication.UI.Star1;
import com.example.firstmyapplication.UI.Star2;
import com.example.firstmyapplication.UI.Star3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private void performCommonTask() {  // 공통 작업 수행
    }
    private DrawerLayout drawerLayout;
    private View drawerView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Login_Activity login_activity; // 로그인 프래그먼트
    private Star1 Star1;
    private Star2 Star2;
    private Star3 Star3;
    private Share Share;
    private Health Health;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        performCommonTask();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        NavigationView navigationView = findViewById(R.id.Main_Info);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        intent = new Intent(MainActivity.this, Profile_Activity.class); // 예시 클래스, 실제 클래스로 변경 필요
                        startActivity(intent);
                        break;

                    case R.id.nav_school: // nav_profile이라는 id를 가진 메뉴 항목에 대한 처리
                        intent = new Intent(MainActivity.this, SchoolInfo_Activity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_job:
                        intent = new Intent(MainActivity.this, JobInfo_Activity.class);
                        startActivity(intent);
                        break;

                    /*case R.id.nav_job_info:
                        intent = new Intent(MainActivity.this, JobInfoActivity.class);
                        startActivity(intent);
                        break;*/

                    // 추가하고 싶은 다른 메뉴 항목들에 대한 처리를 이곳에 추가할 수 있습니다.

                    default:
                        return false;
                }
                drawerLayout.closeDrawers(); // 메뉴 항목을 클릭한 후에는 드로어를 닫아줍니다.
                return true;
            }
        });

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
            login_activity = new Login_Activity();
            Star1 = new Star1();
            Star2 = new Star2();
            Star3 = new Star3();
            Share = new Share();
            Health = new Health();

            setLogin_activity();  //첫 프레그먼트 화면을 무엇으로 지정해 줄 것인지 선택.

        }
        private void setLogin_activity () {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, login_activity)
                    .commit();
        }

        public void onLoginSuccess () {
            setFrag(0);

            // 바텀 네비게이션 다시 표시
            BottomNavigationView bottomNav = findViewById(R.id.bottomNavi);
            if (bottomNav != null) {
                bottomNav.setVisibility(View.VISIBLE);
            }
        }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

        private void setFrag ( int n){
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

        @Override
        protected void onResume () {
            super.onResume();

            // SharedPreferences에서 데이터 로드
            SharedPreferences sharedPreferences = getSharedPreferences("ProfileInfo", MODE_PRIVATE);
            String photoUriStr = sharedPreferences.getString("PhotoUri", "");
            String name = sharedPreferences.getString("Name", "_____");
            String studentNumber = sharedPreferences.getString("Food", "__________");
            String birthDate = sharedPreferences.getString("BirthDate", "________");

            // 드로어 레이아웃의 ImageView에 사진 표시
            if (!photoUriStr.equals("")) {
                ImageView profileImageView = findViewById(R.id.imageView);
                profileImageView.setImageURI(Uri.parse(photoUriStr));
            }

            // 드로어 레이아웃의 TextView에 정보 표시
            // 예를 들어, drawerLayout에 이름, 학번, 생년월일을 표시하는 TextView가 있다고 가정합니다.
            TextView nameTextView = drawerLayout.findViewById(R.id.textView26);
            TextView studentNumberTextView = drawerLayout.findViewById(R.id.textView27);
            TextView birthDateTextView = drawerLayout.findViewById(R.id.textView28);

            nameTextView.setText(name);
            studentNumberTextView.setText(studentNumber);
            birthDateTextView.setText(birthDate);
        }
    }

