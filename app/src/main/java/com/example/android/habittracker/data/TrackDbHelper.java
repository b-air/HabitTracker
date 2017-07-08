package com.example.android.habittracker.data;

/**
 * Created by bruno on 08/07/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.TrackContract.TrackEntry;

/**
 * Database helper for Track Habit APp
 */
public class TrackDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TrackDbHelper.class.getSimpleName();

    /** NAME of Database file */
    private static final String DATABASE_NAME = "Tracks.db";

    /** Database Version */
    private static final int DATABASE_VERSION = 1;

    /** Construct new instance */
    public TrackDbHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    /** Called when first created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL STATEMENT
        String SQL_CREATE_TRACKS_TABLE = "CREATE TABLE " + TrackEntry.TABLE_NAME + " ("
                + TrackEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TrackEntry.COLUMN_TRACK_WEIGHT + " INTEGER NOT NULL,"
                + TrackEntry.COLUMN_TRACK_KILOMETERS + " INTEGER NOT NULL DEFAULT 0,"
                + TrackEntry.COLUMN_TRACK_EXERCISE + " INTEGER NOT NULL DEFAULT 0,"
                + TrackEntry.COLUMN_TRACK_JUNKFOOD + " INTEGER NOT NULL DEFAULT 0);";

        //EXECUTE STATEMENT
        db.execSQL(SQL_CREATE_TRACKS_TABLE);

    }

    /** Called when upgraded */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no upgrade planned
    }
}
