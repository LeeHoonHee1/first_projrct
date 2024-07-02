package com.example.firstmyapplication.star2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firstmyapplication.R;

public class Star2Fragment extends Fragment {

    public Star2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star2, container, false);

        ImageView buttonOpenDetail = view.findViewById(R.id.img_bean_paste);
        buttonOpenDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailFragment();
            }
        });

        return view;
    }

    private void openDetailFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, new Star2DeltailFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
