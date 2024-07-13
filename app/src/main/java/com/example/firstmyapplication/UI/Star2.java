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

public class Star2 extends Fragment {

    public Star2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star2, container, false);

        // Ensure ImageViews are clickable
        ImageView buttonOpenDetail3 = view.findViewById(R.id.img_bean_paste);
        buttonOpenDetail3.setClickable(true);

        ImageView buttonOpenDetail4 = view.findViewById(R.id.img_tomato_pasta);
        buttonOpenDetail4.setClickable(true);


        buttonOpenDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment
                openDetailFragment3();
            }
        });


        buttonOpenDetail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show Toast message
                Toast.makeText(getActivity(), "상세 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();

                // Open detail fragment 2
                openDetailFragment4();
            }
        });

        return view;
    }

    private void openDetailFragment3() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star2Detail1());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openDetailFragment4() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new Star2Detail2());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
