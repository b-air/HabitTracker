package com.example.android.habittracker.data;

/**
 * Created by bruno on 08/07/2017.
 */

import android.provider.BaseColumns;

/**
 * API for Track Habit App
 */
public class TrackContract {

    //prevent accidental instantiating
    private TrackContract(){}

    //Define constant values
    public static final class TrackEntry implements BaseColumns {

        /**
         * Database Name
         **/
        public final static String TABLE_NAME = "tracks";

        /**
         *  ID INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         *  Weight INTEGER
         */
        public final static String COLUMN_TRACK_WEIGHT = "weight";

        /**
         *  Kilometers INTEGER
         */
        public final static String COLUMN_TRACK_KILOMETERS = "km";

        /**
         * Exercise INTEGER
         */
        public final static String COLUMN_TRACK_EXERCISE = "Training";

        /**
         *  JunkFood INTEGER
         */
        public final static String COLUMN_TRACK_JUNKFOOD = "JunkFood";

        /**
         * Values for Exercise
         */
        public static final int EXERCISE_NOPE = 0;
        public static final int EXERCISE_DONE = 1;

        /**
         * Values for JunkFood
         */
        public static final int JUNKFOOD_NOPE = 0;
        public static final int JUNKFOOD_YES = 1;
    }
}
