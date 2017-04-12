package stupidcat.me.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stupidcat.me.finalproject.Network.IRCClient;
import stupidcat.me.finalproject.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        switch (v.getId()) {
            case R.id.MainView_connectButton:
                intent = new Intent(this, ChannelActivity.class);
                startActivity(intent);
                break;
            case R.id.MainView_settingsButton:
                intent = new Intent(this, PrefsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
