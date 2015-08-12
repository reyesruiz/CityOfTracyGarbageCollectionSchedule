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
       // new Waste(MainActivity.this);
        setContentView(R.layout.activity_main);

        Time timenow  = new Time();
        timenow.setToNow();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timenow.toMillis(false));
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        int daynumber = cal.get(Calendar.DAY_OF_WEEK);
        String sideoftracyblvd = "east";
        boolean even;
        String message;
        if (sideoftracyblvd == "east") {
            //Nothing needs to be done, since east will be used as default
        }
        else {
            //This will be for the west side of Tracy Bldv.
            //Adding 1 to the weeofyear to make it opposite as east,
            weekOfYear = weekOfYear + 1;
        }
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
        if ((weekOfYear % 2 ) == 0) {
            even = true;
        }
        else {
            even = false;
        }

//Using recycle as even.

        String yardorecycle;
        String descyardorrecycle;
        Integer imageyardorrecycle;

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
        Waste waste = Waste.wastes[2];
        String descGarbage = getString(waste.getDescription());
        Integer imagegarbage = waste.getImageID();
        String garbage = getString(waste.getName());
        //String and = getString(R.string.and);
        //yardorecycle = garbage + " " + and + " " + yardorecycle;
        String turn = getString(R.string.turn);
        String date = DateUtils.formatDateTime(this, timenow.toMillis(false), DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR);
        String todaysformatted = getString(R.string.today) +" " + date;
        TextView todaysdate = (TextView) findViewById(R.id.tv_todays_date);
        todaysdate.setText(todaysformatted);
        TextView messageview = (TextView) findViewById(R.id.tv_message);
        messageview.setText(message);
        TextView turnview = (TextView) findViewById(R.id.tv_turn);
        turnview.setText(turn);
        TextView recycleoryardview = (TextView) findViewById(R.id.tv_recycleoryard);
        recycleoryardview.setText(yardorecycle);
        TextView garbabeview = (TextView) findViewById(R.id.tv_garbage);
        garbabeview.setText(getString(R.string.garbage));
        TextView descGarbageview = (TextView) findViewById(R.id.tv_descGarbage);
        TextView descRecycleorYardview = (TextView) findViewById(R.id.tv_descRecycleorYard);
        descGarbageview.setText(descGarbage);
        descRecycleorYardview.setText(descyardorrecycle);
        ImageView garbageimageview = (ImageView) findViewById(R.id.iv_garbage);
        garbageimageview.setImageResource(imagegarbage);
        ImageView yardorrecycleimageview = (ImageView) findViewById(R.id.iv_yardorrecycle);
        yardorrecycleimageview.setImageResource(imageyardorrecycle);
    }


}
