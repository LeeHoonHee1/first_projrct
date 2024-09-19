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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstmyapplication.R;

public class Share extends Fragment {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private Cursor cursor;

    private static final int ADD_POST_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        dbHelper = new DatabaseHelper(requireActivity());
        database = dbHelper.getReadableDatabase();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cursor = getPosts();
        postAdapter = new PostAdapter(getActivity(), cursor);
        recyclerView.setAdapter(postAdapter);

        Button addPostButton = view.findViewById(R.id.addPostButton);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPostActivity.class);
                startActivityForResult(intent, ADD_POST_REQUEST_CODE);  // AddPostActivity 호출
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
        cursor = getPosts();
        postAdapter.swapCursor(cursor);  // 리스트 갱신
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_POST_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            // 게시글을 추가한 후 리스트를 갱신
            cursor = getPosts();
            postAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }
}

