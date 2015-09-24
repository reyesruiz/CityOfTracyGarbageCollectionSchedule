package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.text.DateFormatSymbols;
import java.util.Calendar;


public class MainActivity extends Activity {

    private static final int RESULT_SETTINGS = 1;
    private int notification_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIntent().setAction("Already created");
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "5E4yuBOgV5WPq87t9rrW1IYA7mooo9PAPHkIXQd7", "NVpJ3CtrRA1518YAOFdqH6bke8Of1s5q7kCpZs3Z");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        setContentView(R.layout.activity_main);
        //Declaring references to access the views.
        TextView todaysdate = (TextView) findViewById(R.id.tv_todays_date);
        TextView messageview = (TextView) findViewById(R.id.tv_message);
        TextView turnview = (TextView) findViewById(R.id.tv_turn);
        TextView recycleoryardview = (TextView) findViewById(R.id.tv_recycleoryard);
        TextView garbabeview = (TextView) findViewById(R.id.tv_garbage);
        TextView descGarbageview = (TextView) findViewById(R.id.tv_descGarbage);
        TextView descRecycleorYardview = (TextView) findViewById(R.id.tv_descRecycleorYard);
        ImageView garbageimageview = (ImageView) findViewById(R.id.iv_garbage);
        ImageView yardorrecycleimageview = (ImageView) findViewById(R.id.iv_yardorrecycle);

        String selectside = getString(R.string.select_side);
        String selectday = getString(R.string.select_day);
        String sideoftracyblvd = GetMySharedPrefs("sideTracy");
        if (sideoftracyblvd == "NULL"){
            Toast toast = Toast.makeText(this, selectside, Toast.LENGTH_LONG);
            toast.show();
           // Intent i = new Intent(this, SettingsActivity.class);
           // startActivity(i);

            this.finish();
        }
        else{
            //Nothing
        }
        String dayofpickup = GetMySharedPrefs("daychoosen");
        if (dayofpickup == "NULL"){
            dayofpickup = "7";          //Assigning a number so app doesn't crash
            Toast toast = Toast.makeText(this, selectday, Toast.LENGTH_LONG);
            toast.show();
         //   Intent i = new Intent(this, SettingsActivity.class);
         //   startActivity(i);
            this.finish();
        }
       // dayofpickup = "2";
        //if (dayofpickup.equalsIgnoreCase("NULL")){
        //    dayofpickup = "1";
       // }
        //else{
            //Nothing
        //}
        //Time and Calendar
        Time timenow  = new Time();
        Calendar cal = Calendar.getInstance();

        //set time and calendar to now.
        timenow.setToNow();
        cal.setTimeInMillis(timenow.toMillis(false));

        //declaring Variables
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        int daynumber = cal.get(Calendar.DAY_OF_WEEK);

        Log.i("side", sideoftracyblvd);

        boolean even;
        String message;
        String yardorecycle;
        String descyardorrecycle;
        int imageyardorrecycle;
        Log.i("side_week1", Integer.toString(weekOfYear));
        if (sideoftracyblvd.equals("east")) {
            Log.i("side_this", "this is east");
            //Nothing needs to be done, since east will be used as default
        }
        else {
            //This will be for the west side of Tracy Bldv.
            //Adding 1 to the weeofyear to make it opposite as east,
            Log.i("side_this", "this is west");
            weekOfYear = weekOfYear + 1;
        }
        Log.i("side_week2", Integer.toString(weekOfYear));

        //Now using Monday as a pickup day, this will be set in Settings page in later version.

        Integer dayofpickupint = Integer.parseInt(dayofpickup);

        String[] daysofweek = new DateFormatSymbols().getWeekdays();
        String thedayofpickup = daysofweek[dayofpickupint];
        if (daynumber <= dayofpickupint) {

            if (daynumber == dayofpickupint - 1) {
                message = getString(R.string.dayofputtingbinsout);

            } else if (daynumber == dayofpickupint) {
                message = getString(R.string.dayofpickup);

            } else {
                message = getString(R.string.nextpickup) + " " + thedayofpickup;
            }
        }
        else {
            message = getString(R.string.nextpickup) + " " + thedayofpickup;
            weekOfYear = weekOfYear+1;
        }


        //Figuring out if it is even or odd week number
        if ((weekOfYear % 2 ) == 0) {
            even = true;
            Log.i("side_even", Integer.toString(weekOfYear)+"is even");
        }
        else {
            even = false;
            Log.i("side_even", Integer.toString(weekOfYear)+"not even");
        }

        //Using recycle as even.
        if ((even == true)){
            Waste waste = Waste.wastes[0];
            yardorecycle = getString(waste.getName());
            descyardorrecycle = getString(waste.getDescription());
            imageyardorrecycle = waste.getImageID();
        }
        else {
            Waste waste = Waste.wastes[1];
            yardorecycle = getString(waste.getName());
            descyardorrecycle = getString(waste.getDescription());
            imageyardorrecycle = waste.getImageID();
        }

        //Garbage is every week, so no need to calculate anything.
        Waste waste = Waste.wastes[2];
        String descGarbage = getString(waste.getDescription());
        Integer imagegarbage = waste.getImageID();
        String garbage = getString(waste.getName());

        //Getting and setting the strings of the messages to be displayed
        String turn = getString(R.string.turn);
        String date = DateUtils.formatDateTime(this, timenow.toMillis(false), DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        String todaysformatted = getString(R.string.today) +" " + date;

        //Setting text and images to the display.
        todaysdate.setText(todaysformatted);
        messageview.setText(message);
        turnview.setText(turn);
        recycleoryardview.setText(yardorecycle);
        garbabeview.setText(getString(R.string.garbage));
        descGarbageview.setText(descGarbage);
        descRecycleorYardview.setText(descyardorrecycle);
        garbageimageview.setImageResource(imagegarbage);
        yardorrecycleimageview.setImageResource(imageyardorrecycle);

   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;

        }

        return true;
    }


    @Override
    public void onResume(){
        String action = getIntent().getAction();
        if(action == null || !action.equals("Already created")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            getIntent().setAction(null);
        }

        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }


    public String GetMySharedPrefs(String requestedsetting){
        SharedPreferences SharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sharedsetting = SharedPrefs.getString(requestedsetting, "NULL");


        return sharedsetting;
    }

}

