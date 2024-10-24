package com.example.firstmyapplication.Drawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstmyapplication.R;

public class Calorie_Activity extends AppCompatActivity {

    private Spinner foodSpinner;
    private EditText quantityInput;
    private TextView resultText;

    // 음식 항목별 100g당 칼로리 및 탄단지 데이터 (가상의 데이터)
    private final double[][] nutritionData = {
            {107, 20.2, 3.2, 1.5},       // 비빔국수
            {304, 32.9, 9.8, 14.6},   // 샌드위치
            {41, 3.9, 3.1, 1.5},     // 된장찌개
            {104, 20.8, 4.1, 0.7},      // 토마토 파스타
            {193, 7.4, 13.3, 12.3},     // 갈비찜
            {368, 19.1, 10.1, 27.4}   // 비프 웰리턴
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        foodSpinner = findViewById(R.id.food_spinner);
        quantityInput = findViewById(R.id.quantity_input);
        resultText = findViewById(R.id.result_text);

        // Spinner에 음식 목록을 설정
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);

        // Spinner에서 음식 선택 시 자동으로 영양 성분 계산
        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateNutrition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                resultText.setText("음식을 선택하세요.");
            }
        });
    }

    private void calculateNutrition(int selectedFoodIndex) {
        String quantityStr = quantityInput.getText().toString();

        if (quantityStr.isEmpty()) {
            resultText.setText("섭취량을 입력하세요.");
            return;
        }

        double quantity = Double.parseDouble(quantityStr);

        // 음식별 영양 데이터를 가져옴
        double[] selectedFoodData = nutritionData[selectedFoodIndex];

        // 섭취한 양에 따른 영양소 계산
        double calories = selectedFoodData[0] * (quantity / 100);
        double carbs = selectedFoodData[1] * (quantity / 100);
        double protein = selectedFoodData[2] * (quantity / 100);
        double fat = selectedFoodData[3] * (quantity / 100);

        // 결과 표시
        String result = String.format("총 칼로리: %.2f kcal\n탄수화물: %.2f g\n단백질: %.2f g\n지방: %.2f g",
                calories, carbs, protein, fat);
        resultText.setText(result);
    }
}