package stupidcat.me.finalproject.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import stupidcat.me.finalproject.R;

/**
 * Created by cat on 2017-04-06.
 */

public class PrefsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    boolean dirty = false;

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs,
                                          String key) {
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
