package com.digitalruiz.cityoftracygarbagecollectionschedule;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.logging.Handler;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MySettingsFragment()).commit();
    }

    public static class MySettingsFragment extends PreferenceFragment implements TimePickerDialog.OnTimeSetListener {


        @Override
        public void onCreate(final Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            Preference btnTimeFilter = (Preference) findPreference("btnTimeFilter");
            btnTimeFilter.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showTimeDialog();
                    return false;
                }
            });
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i2){
            Log.i("timepicker", "hour "+i+" minute "+i2);

            SharedPreferences preferences = getPreferenceManager().getSharedPreferences();
            preferences.edit().putString("btnTimeFilterHour", Integer.toString(i)).putString("btnTimeFilterMinute", Integer.toString(i2)).commit();


        }


        private void showTimeDialog(){
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            new TimePickerDialog(getActivity(), this, hour, minute, false).show();
        }


    }







}
