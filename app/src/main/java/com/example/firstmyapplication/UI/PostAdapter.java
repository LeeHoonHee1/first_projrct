package com.example.firstmyapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstmyapplication.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Cursor cursor;
    private LayoutInflater inflater;
    private OnItemActionListener listener;

    // 인터페이스 정의: 수정 및 삭제 시 처리할 이벤트를 전달
    public interface OnItemActionListener {
        void onEdit(int id, String title, String content);  // 수정 시 호출
        void onDelete(int id);  // 삭제 시 호출
    }

    // 생성자에 Context와 리스너를 추가하여 초기화
    public PostAdapter(Context context, Cursor cursor, OnItemActionListener listener) {
        this.cursor = cursor;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

        holder.titleTextView.setText(title);
        holder.contentTextView.setText(content);

        // 수정 버튼 클릭 이벤트 처리
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEdit(id, title, content);
            }
        });

        // 삭제 버튼 클릭 이벤트 처리
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (cursor != null) ? cursor.getCount() : 0;
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor = newCursor;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
        Button editButton;
        Button deleteButton;

        ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

