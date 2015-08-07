package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.TextView;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Time timenow  = new Time();
        timenow.setToNow();
        String date = DateUtils.formatDateTime(this, timenow.toMillis(false), DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR);
        String todaysformatted = "Todays Date is: " + date;
        TextView todaysdate = (TextView) findViewById(R.id.tv_todays_date);
        todaysdate.setText(todaysformatted);
    }


}
