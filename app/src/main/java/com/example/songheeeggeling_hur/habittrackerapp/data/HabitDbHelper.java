package com.example.songheeeggeling_hur.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by songheeeggeling-hur on 06.07.17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public final static String LOG_TAG = HabitDbHelper.class.getSimpleName();
    private final static String DATABASE_NAME = "habits.db";
    private final static int DATABASE_VERSION = 5;

    public HabitDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME +"("
                + HabitContract.HabitEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_NAME +" TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_TIME + " INTEGER NOT NULL DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_HABIT_STATUS + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME);
        onCreate(db);
    }
}
