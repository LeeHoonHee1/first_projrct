package com.example.firstmyapplication.star1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstmyapplication.R;

public class Star1DeltailFragment extends Fragment {

    public Star1DeltailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star1_detail, container, false);

        TextView buttonOpenDetail = view.findViewById(R.id.tv_back);
        buttonOpenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailFragment();
            }
        });

        return view;
    }

    private void openDetailFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star1Fragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
} // 커밋 테스트
