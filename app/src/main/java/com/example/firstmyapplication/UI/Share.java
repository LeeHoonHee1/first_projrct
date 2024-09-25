package com.example.firstmyapplication.UI;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;  // Intent 추가
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;  // Button 추가
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstmyapplication.R;

public class Share extends Fragment implements PostAdapter.OnItemActionListener {

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
        postAdapter = new PostAdapter(getActivity(), cursor, this);
        recyclerView.setAdapter(postAdapter);

        // AddPostActivity로 이동하는 코드 추가
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
    public void onEdit(int id, String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Edit Post");

        View dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_edit_post, null);
        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        EditText contentEditText = dialogView.findViewById(R.id.contentEditText);

        titleEditText.setText(title);
        contentEditText.setText(content);

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newTitle = titleEditText.getText().toString();
                String newContent = contentEditText.getText().toString();

                ContentValues values = new ContentValues();
                values.put("title", newTitle);
                values.put("content", newContent);

                // 데이터베이스에서 해당 게시글 수정
                database.update("posts", values, "id = ?", new String[]{String.valueOf(id)});
                cursor = getPosts();
                postAdapter.swapCursor(cursor);
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    @Override
    public void onDelete(int id) {
        new AlertDialog.Builder(requireActivity())
                .setTitle("Delete Post")
                .setMessage("이 게시물을 삭제하시겠습니까?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 데이터베이스에서 해당 게시글 삭제
                        database.delete("posts", "id = ?", new String[]{String.valueOf(id)});
                        cursor = getPosts();
                        postAdapter.swapCursor(cursor);
                    }
                })
                .setNegativeButton("No", null)
                .create()
                .show();
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

    // AddPostActivity에서 돌아온 후 데이터 갱신
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_POST_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            // 새 게시글 추가 후 데이터 갱신
            cursor = getPosts();
            postAdapter.swapCursor(cursor);
        }
    }
}
