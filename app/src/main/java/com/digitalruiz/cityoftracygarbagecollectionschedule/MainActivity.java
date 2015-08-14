package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //Time and Calendar
        Time timenow  = new Time();
        Calendar cal = Calendar.getInstance();

        //set time and calendar to now.
        timenow.setToNow();
        cal.setTimeInMillis(timenow.toMillis(false));

        //declaring Variables
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        int daynumber = cal.get(Calendar.DAY_OF_WEEK);
        String sideoftracyblvd = "east";
        boolean even;
        String message;
        String yardorecycle;
        String descyardorrecycle;
        int imageyardorrecycle;

        // Side of tracy blvd, this will be set via Settings in later version
        if (sideoftracyblvd == "east") {
            //Nothing needs to be done, since east will be used as default
        }
        else {
            //This will be for the west side of Tracy Bldv.
            //Adding 1 to the weeofyear to make it opposite as east,
            weekOfYear = weekOfYear + 1;
        }

        //Now using Monday as a pickup day, this will be set in Settings page in later version.
        if (daynumber == 1){
            message = getString(R.string.dayofputtingbinsout);

        }
        else if (daynumber == 2){
            message = getString(R.string.dayofpickup);

        }
        else {
            message = getString(R.string.nextpickup);
            weekOfYear = weekOfYear+1;

        }


        //Figuring out if it is even or odd week number
        if ((weekOfYear % 2 ) == 0) {
            even = true;
        }
        else {
            even = false;
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


}
