package com.example.firstmyapplication.Drawer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.firstmyapplication.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Profile_Activity extends AppCompatActivity {

    Bitmap bitmap;
    ImageView imageView;
    EditText name, food;
    Button birthday, saveButton;
    Date curDate = new Date();
    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    // 권한 요청 코드 정의
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // UI 컴포넌트 초기화
        imageView = findViewById(R.id.imageView);
        Button picBtn = findViewById(R.id.pic_btn);
        picBtn.setOnClickListener(view -> {
            // 카메라 권한 확인 및 요청
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            } else {
                // 권한이 허용되면 카메라 호출
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultPicture.launch(intent);
            }
        });

        // 사용자 입력 UI 컴포넌트 초기화
        name = findViewById(R.id.editTextTextPersonName);
        food = findViewById(R.id.editTextTextPersonName2);
        birthday = findViewById(R.id.button);
        saveButton = findViewById(R.id.button2);

        // 날짜 초기화 및 버튼 리스너 설정
        birthday.setText(dataFormat.format(curDate));
        birthday.setOnClickListener(view -> showDateDialog());

        // 저장 버튼 리스너 설정
        saveButton.setOnClickListener(view -> {
            String nameStr = name.getText().toString();
            String foodStr = food.getText().toString();
            String birthStr = birthday.getText().toString();

            // SharedPreferences에 저장
            SharedPreferences sharedPreferences = getSharedPreferences("ProfileInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Name", nameStr);
            editor.putString("Food", foodStr);
            editor.putString("BirthDate", birthStr);
            editor.apply();

            // 저장 완료 메시지 표시
            Toast.makeText(getApplicationContext(), "저장됨", Toast.LENGTH_SHORT).show();
        });
    }

    // 카메라 결과 처리
    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        bitmap = (Bitmap) extras.get("data");
                        imageView.setImageBitmap(bitmap);

                        // 사진 URI를 SharedPreferences에 저장
                        Uri imageUri = getImageUri(getApplicationContext(), bitmap);
                        SharedPreferences sharedPreferences = getSharedPreferences("ProfileInfo", MODE_PRIVATE);
                        sharedPreferences.edit().putString("PhotoUri", imageUri.toString()).apply();
                    }
                }
            });

    // Bitmap을 Uri로 변환하는 메소드
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    // 생년월일 선택 관련 코드
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        try {
            curDate = dataFormat.parse(birthday.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        calendar.setTime(curDate);
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, birthDateSetListener, curYear, curMonth, curDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener birthDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, month);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, day);
            Date newDate = selectedCalendar.getTime();
            birthday.setText(dataFormat.format(newDate));
        }
    };

    // 권한 요청 결과 처리
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되면 카메라 호출
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultPicture.launch(intent);
            } else {
                // 권한이 거부되면 사용자에게 메시지 표시
                Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
