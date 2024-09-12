package com.example.firstmyapplication.UI;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "board.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        // 내부 저장소에 데이터베이스를 저장하도록 경로를 설정
        super(context, context.getDatabasePath(DATABASE_NAME).getPath(), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // posts 테이블 생성
        db.execSQL("CREATE TABLE posts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블 삭제 후 다시 생성
        db.execSQL("DROP TABLE IF EXISTS posts");
        onCreate(db);
    }
}


