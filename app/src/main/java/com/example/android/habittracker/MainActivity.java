package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.TrackDbHelper;
import com.example.android.habittracker.data.TrackContract.TrackEntry;

public class  MainActivity extends AppCompatActivity {

    private TrackDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new TrackDbHelper(this);

        displayDatabaseInfo();
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertTrack();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteTracks();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertTrack(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackEntry.COLUMN_TRACK_WEIGHT, 72);
        values.put(TrackEntry.COLUMN_TRACK_KILOMETERS, 8);
        values.put(TrackEntry.COLUMN_TRACK_EXERCISE, TrackEntry.EXERCISE_DONE);
        values.put(TrackEntry.COLUMN_TRACK_JUNKFOOD, TrackEntry.JUNKFOOD_NOPE);

        long newRowId = db.insert(TrackEntry.TABLE_NAME, null, values);

        Log.v("CatalogActivity", "New row ID" + newRowId);

    }

    private void deleteTracks(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(TrackEntry.TABLE_NAME,null,null);
    }

    private void displayDatabaseInfo(){
        //Instanciate subclass of SQLiteOpenHelper
        TrackDbHelper mDbHelper = new TrackDbHelper(this);

        //Create Open database
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Set projection
        String[] projection = {
                TrackEntry._ID,
                TrackEntry.COLUMN_TRACK_WEIGHT,
                TrackEntry.COLUMN_TRACK_KILOMETERS,
                TrackEntry.COLUMN_TRACK_EXERCISE,
                TrackEntry.COLUMN_TRACK_JUNKFOOD
        };

        //Cursor for query()
        Cursor cursor = db.query(
                TrackEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_track);

        try{
            // Quick View of database
            displayView.setText("The track table contains " + cursor.getCount() + " tracks.\n\n");
            displayView.append(TrackEntry._ID + " - " +
                    TrackEntry.COLUMN_TRACK_WEIGHT + " - " +
                    TrackEntry.COLUMN_TRACK_KILOMETERS + " - " +
                    TrackEntry.COLUMN_TRACK_EXERCISE + " - " +
                    TrackEntry.COLUMN_TRACK_JUNKFOOD + "\n");


            int idColumnIndex = cursor.getColumnIndex(TrackEntry._ID);
            int weightColumnIndex = cursor.getColumnIndex(TrackEntry.COLUMN_TRACK_WEIGHT);
            int kilometersColumnIndex = cursor.getColumnIndex(TrackEntry.COLUMN_TRACK_KILOMETERS);
            int exerciseColumnIndex = cursor.getColumnIndex(TrackEntry.COLUMN_TRACK_EXERCISE);
            int junkfoodColumnIndex = cursor.getColumnIndex(TrackEntry.COLUMN_TRACK_JUNKFOOD);

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(weightColumnIndex);
                String currentBreed = cursor.getString(kilometersColumnIndex);
                int currentGender = cursor.getInt(exerciseColumnIndex);
                int currentWeight = cursor.getInt(junkfoodColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }

        } finally {
            //releasing resources
            cursor.close();
        }

    }

}
