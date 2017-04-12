package stupidcat.me.finalproject.Activities;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import stupidcat.me.finalproject.R;

/**
 * Created by cat on 2017-04-06.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {

    }

    public String getResString(int key) {
        return getResources().getString(key);
    }

    public String getPrefsString(int key, int defaultVal) {
        return prefs.getString(getResString(key), getResString(defaultVal));
    }
}
