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

public class Star3 extends Fragment {

    public Star3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star3, container, false);

        // Ensure ImageViews are clickable
        ImageView buttonOpenDetail5 = view.findViewById(R.id.img_braised_short_ribs);
        buttonOpenDetail5.setClickable(true);

        ImageView buttonOpenDetail6 = view.findViewById(R.id.img_beef_wellington);
        buttonOpenDetail6.setClickable(true);


        buttonOpenDetail5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment
                openDetailFragment5();
            }
        });


        buttonOpenDetail6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment 2
                openDetailFragment6();
            }
        });

        return view;
    }

    private void openDetailFragment5() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star3Detail1());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openDetailFragment6() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star3Detail2());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
