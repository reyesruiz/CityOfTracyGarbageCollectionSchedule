package com.digitalruiz.cityoftracygarbagecollectionschedule;



import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.TimePicker;
import java.util.Calendar;



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
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

            Preference btnTimeFilter = (Preference) findPreference("btnTimeFilter");
            btnTimeFilter.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String hour = GetMySharedPrefs("btnTimeFilterHour");
                    String minute = GetMySharedPrefs("btnTimeFilterMinute");
                    showTimeDialog(hour, minute);  //Will send NULL if none set.
                    return false;
                }
            });


        }

        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            Log.i("timepicker", "hour " + i + " minute " + i2);

            SharedPreferences preferences = getPreferenceManager().getSharedPreferences();
            preferences.edit().putString("btnTimeFilterHour", Integer.toString(i)).putString("btnTimeFilterMinute", Integer.toString(i2)).commit();
            this.getActivity().onBackPressed();

        }


        private void showTimeDialog(String hour, String minute) {
            int hour_int;
            int minute_int;
            if (hour == "NULL" || minute == "NULL"){
                final Calendar calendar = Calendar.getInstance();
                hour_int = calendar.get(Calendar.HOUR_OF_DAY);
                minute_int = calendar.get(Calendar.MINUTE);

            }
            else {
                hour_int = Integer.valueOf(hour);
                minute_int = Integer.valueOf(minute);
            }
            new TimePickerDialog(getActivity(), this, hour_int, minute_int, false).show();


        }

        public String GetMySharedPrefs(String requestedsetting){
            SharedPreferences SharedPrefs = getPreferenceManager().getSharedPreferences();
            String sharedsetting = SharedPrefs.getString(requestedsetting, "NULL");
            return sharedsetting;
        }
    }

    @Override
    public void onBackPressed() {
        getmeback();
        super.onBackPressed();
    }

    public void getmeback(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}