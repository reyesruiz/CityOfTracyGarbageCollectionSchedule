package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Time timenow  = new Time();
        timenow.setToNow();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timenow.toMillis(false));
        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
        boolean even;
        if ((weekOfYear % 2 ) == 0) {
            even = true;
        }
        else {
            even = false;
        }
        String recycleoryard;
        if ((even == true)){
            recycleoryard = "Yard Waste";
        }
        else {
            recycleoryard = "Recycle";
        }
        String date = DateUtils.formatDateTime(this, timenow.toMillis(false), DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR);
        String todaysformatted = "Todays Date is: " + date + " which is in Week number: " + weekOfYear + " so this weeks is " + recycleoryard + " turn";
        TextView todaysdate = (TextView) findViewById(R.id.tv_todays_date);
        todaysdate.setText(todaysformatted);
    }


}
