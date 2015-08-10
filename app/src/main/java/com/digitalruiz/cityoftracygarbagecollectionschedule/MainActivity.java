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
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timenow.toMillis(false));
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        int daynumber = cal.get(Calendar.DAY_OF_WEEK);
        boolean even;
        String message;
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

//Using yard as even.
        String yardorecycle;
        if ((even == true)){
            yardorecycle = getString(R.string.yard);
        }
        else {
            yardorecycle = getString(R.string.recycle);
        }
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
    }


}
