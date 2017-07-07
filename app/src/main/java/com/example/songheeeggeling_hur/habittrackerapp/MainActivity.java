package com.example.songheeeggeling_hur.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.songheeeggeling_hur.habittrackerapp.data.HabitContract;
import com.example.songheeeggeling_hur.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newHabit ("Habit0","0",0);
        newHabit ("Habit1","15",1);
        newHabit ("Habit2","30",1);

        Cursor cursor =read();
        try {
            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TIME);
            int statusColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_STATUS);
            while (cursor.moveToNext()) {
                int currentID =cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);

                Log.v(LOG_TAG, "id: "+currentID+ "name: "+currentName
                +"time: "+currentTime+ "status: "+ currentStatus);

            }
        } finally {
            cursor.close();
        }
    }
    public void newHabit(String name, String time, int status){
        Integer mTime =Integer.parseInt(time);
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME,name);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_TIME,mTime);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_STATUS,status);


        long newRowID = db.insert(HabitContract.HabitEntry.TABLE_NAME,null,values);

    }

    public Cursor read(){
        HabitDbHelper habitDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_TIME,
                HabitContract.HabitEntry.COLUMN_HABIT_STATUS
        };

        return db.query(HabitContract.HabitEntry.TABLE_NAME,projection,null,null,null,null,null);
    }
}
