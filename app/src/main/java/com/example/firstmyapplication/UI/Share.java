package com.example.firstmyapplication.UI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstmyapplication.R;

public class Share extends Fragment {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        database = dbHelper.getReadableDatabase();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        postAdapter = new PostAdapter(getActivity(), getPosts());
        recyclerView.setAdapter(postAdapter);

        Button addPostButton = view.findViewById(R.id.addPostButton);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPostActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private Cursor getPosts() {
        return database.query("posts", new String[]{"id", "title", "content"}, null, null, null, null, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        postAdapter.swapCursor(getPosts());
    }


}

