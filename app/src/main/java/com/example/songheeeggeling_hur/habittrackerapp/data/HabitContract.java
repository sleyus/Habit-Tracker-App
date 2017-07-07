package com.example.songheeeggeling_hur.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by songheeeggeling-hur on 06.07.17.
 */

final public class HabitContract {
    private HabitContract(){

    }
    public static final class HabitEntry implements BaseColumns{
        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_HABIT_TIME = "time";
        public final static String COLUMN_HABIT_STATUS = "status";

        public final static int STATUS_NOT_COMPLETED = 0;
        public final static int STATUS_COMPLETED = 1;

    }
}
