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
    private Cursor cursor;  // Cursor를 멤버 변수로 선언하여 관리

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        dbHelper = new DatabaseHelper(requireActivity());  // requireActivity()로 null 방지
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
        cursor = getPosts();  // Cursor 업데이트
        postAdapter.swapCursor(cursor);  // 업데이트된 Cursor 적용
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();  // Cursor 닫기
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }
}
