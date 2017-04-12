package stupidcat.me.finalproject.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import stupidcat.me.finalproject.Adapters.MessageAdapter;
import stupidcat.me.finalproject.Network.IRCClient;
import stupidcat.me.finalproject.R;
import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.Message;
import stupidcat.me.finalproject.Structures.MessageType;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-08.
 */

public class ChannelActivity extends BaseActivity {

    MessageAdapter messageAdapter;
    ArrayList<Message> messageList;
    String channel;
    ListView messageListView;
    IRCClient client;
    ImageButton sendButton;
    EditText inputField;
    private User SystemUser = new User("SYSTEM", "!");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_view);
        this.channel = getPrefsString(R.string.Prefs_channel_key, R.string.Prefs_channel_default);
        client = new IRCClient(this,
                getPrefsString(R.string.Prefs_server_key, R.string.Prefs_server_default), 6666,
                this.channel,
                getPrefsString(R.string.Prefs_username_key, R.string.Prefs_username_default));

        setTitle(this.channel);

        messageListView = (ListView) findViewById(R.id.ChannelView_listView);
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messageList);
        messageListView.setAdapter(messageAdapter);

        sendButton = (ImageButton) findViewById(R.id.ChannelView_submitButton);
        inputField = (EditText) findViewById(R.id.ChannelView_input);

        sendButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.getThread().interrupt();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        String input = inputField.getText().toString();
        if (!input.equals("")) {
            if (input.startsWith("/")) {
                input = input.substring(1);
                List<String> words = Arrays.asList(input.split(" "));
                switch (words.get(0).toUpperCase()) {
                    case "HELP":
                        addSystemMessage("Available Commands:\n"
                                + "  RAW <text> - Executes a raw IRC command\n"
                                + "  ME <text> - Executes an action\n"
                                + "  LIST - Lists the users in the current channel\n"
                                + "  HELP - Shows this menu");
                        break;
                    case "RAW":
                        client.sendRaw(TextUtils.join(" ", words.subList(1, words.size())));
                        break;
                    case "ME":
                        client.send("\u0001ACTION " + TextUtils.join(" ", words.subList(1, words.size())) + "\u0001");
                        break;
                    case "LIST":
                        client.sendRaw("NAMES " + channel);
                        break;
                }
            } else {
                client.send(input);
            }
        }
        inputField.setText("");
    }

    public void addMessage(Message msg) {
        final Message msg2 = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (channel != null && channel.equals(msg2.getChannel().getName()))
                    messageAdapter.add(msg2);
            }
        });
    }

    public void addSystemMessage(String content) {
        addSystemMessage(content, MessageType.SYSTEM);
    }

    public void addSystemMessage(String content, MessageType type) {
        addMessage(new Message(content, SystemUser, new Date(), new Channel(getChannel()), type));
    }


    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
