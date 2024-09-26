package com.example.firstmyapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.firstmyapplication.R;

public class Health extends Fragment {
    Button yesButton, noButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        // findViewById는 view를 통해 호출해야 함
        yesButton = view.findViewById(R.id.yesButton);
        noButton = view.findViewById(R.id.noButton);

        // 첫 번째 버튼 클릭 리스너 - Yes_Activity로 이동
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Yes_Activity로 페이지 이동
                Intent intent = new Intent(getActivity(), Yes_Activity.class);
                startActivity(intent);
            }
        });

        // 두 번째 버튼 클릭 리스너 - No_Activity로 이동
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No_Activity로 페이지 이동
                Intent intent = new Intent(getActivity(), No_Activity.class);
                startActivity(intent);
            }
        });

        return view; // view를 반환
    }
}
