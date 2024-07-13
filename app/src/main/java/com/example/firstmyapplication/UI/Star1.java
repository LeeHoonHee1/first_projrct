package com.example.firstmyapplication.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstmyapplication.R;

public class Star1 extends Fragment {

    public Star1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star1, container, false);

        // Ensure ImageViews are clickable
        ImageView buttonOpenDetail = view.findViewById(R.id.img_noodle);
        buttonOpenDetail.setClickable(true);

        ImageView buttonOpenDetail2 = view.findViewById(R.id.img_sandwich);
        buttonOpenDetail2.setClickable(true);

        // Set OnClickListener for img_noodle
        buttonOpenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment
                openDetailFragment();
            }
        });

        // Set OnClickListener for img_sandwich
        buttonOpenDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment 2
                openDetailFragment2();
            }
        });

        return view;
    }

    private void openDetailFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star1Detail1());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openDetailFragment2() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star1Detail2());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
