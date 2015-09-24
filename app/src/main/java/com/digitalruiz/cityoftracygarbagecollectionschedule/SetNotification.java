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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class SetNotification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);

        String notificationhour = GetMySharedPrefs("btnTimeFilterHour");
        String notificationminute = GetMySharedPrefs("btnTimeFilterMinute");
        if (notificationhour == "NULL" || notificationminute == "NULL"){
            Log.i("timepicker", "No Notification time set yet");
        }
        else {
            int notihour = Integer.valueOf(notificationhour);
            int notminute = Integer.valueOf(notificationminute);

            Log.i("timepickerset", notificationhour + notificationminute);

            String dayofpickup = GetMySharedPrefs("daychoosen");
            if (dayofpickup == "NULL"){
                dayofpickup = "7";          //Assigning a number so app doesn't crash
                String selectday = getString(R.string.select_day);
                Toast toast = Toast.makeText(this, selectday, Toast.LENGTH_LONG);
                toast.show();
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                this.finish();
            }
            Integer dayofpickupint = Integer.parseInt(dayofpickup);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, dayofpickupint-1);
            calendar.set(Calendar.HOUR_OF_DAY, notihour);
            calendar.set(Calendar.MINUTE, notminute);
            calendar.set(Calendar.SECOND, 0);

            scheduleNotification(getNotification("yard"), calendar, "yard", 1);
            scheduleNotification(getNotification("recycle"), calendar, "recycle", 2);
            Toast toast = Toast.makeText(this, "Congrats, notification set", Toast.LENGTH_LONG);
            toast.show();
            Intent i2 = new Intent(this, MainActivity.class);
            startActivity(i2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String GetMySharedPrefs(String requestedsetting){
        SharedPreferences SharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sharedsetting = SharedPrefs.getString(requestedsetting, "NULL");


        return sharedsetting;
    }


    private void scheduleNotification(Notification notification, Calendar calendar, String test, Integer intentid){
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, intentid);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, intentid, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long calendarsetinmillis;
        if (intentid == 1) {
            calendarsetinmillis = calendar.getTimeInMillis();
            Log.i("timemili1", Long.toString(calendarsetinmillis));
        }
        else if (intentid == 2) {
            calendarsetinmillis = (calendar.getTimeInMillis())+(60*1000);
            Log.i("timemili2", Long.toString(calendarsetinmillis));
        }
        else {
            calendarsetinmillis = calendar.getTimeInMillis();
            Log.i("timemili3", Long.toString(calendarsetinmillis));
        }
        //long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendarsetinmillis, 60 * 2 * 1000, pendingIntent);
    }//14 * 24 * 60 * 60 * 1000

    private Notification getNotification(String notification_content){
        Notification.Builder notification = new Notification.Builder(this)
                .setDefaults(-1)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle(getString(R.string.notification_tittle))
                .setContentText(getString(R.string.garbage) + " " + getString(R.string.and) + " " + notification_content)
                .setSmallIcon(R.drawable.garbage_cart)
                .setVibrate(new long[]{1000, 500, 1000})
                .setAutoCancel(true)
                .setVisibility(100);
        return notification.build();

    }
}
