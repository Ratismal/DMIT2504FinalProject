package stupidcat.me.finalproject.Network;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import stupidcat.me.finalproject.Activities.ChannelActivity;
import stupidcat.me.finalproject.Constants.Defaults;
import stupidcat.me.finalproject.Constants.Globals;
import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.IRCEventListener;
import stupidcat.me.finalproject.Structures.Message;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-05.
 */

public class IRCClient {

    IRCThread thread;
    String server;
    int port;
    String username;
    Channel channel;
    ArrayList<User> users = new ArrayList<>();
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private IRCEventListener listener;
    User clientUser;
    ChannelActivity activity;

    public IRCClient(ChannelActivity activity, String server, int port, String channel, String username) {
        System.out.println("Creating an IRC Client");
        this.server = server;
        this.port = port;
        this.channel = new Channel(channel);
        this.username = username;
        this.activity = activity;

        this.thread = new IRCThread(this.server, this.port, new IRCListener(this, activity), queue);
        this.thread.start();

        Globals.ircClient = this;
    }

    public IRCClient(ChannelActivity activity, String server, int port, String channel) {
        this(activity, server, port, channel, Defaults.DEFAULT_USERNAME);
    }

    public IRCClient(ChannelActivity activity, String server, int port) {
        this(activity, server, port, Defaults.DEFAULT_CHANNEL, Defaults.DEFAULT_USERNAME);
    }

    public void send(String input) {
        sendRaw("PRIVMSG " + channel.getName() + " :" + input);
    }

    public void sendRaw(String input) {
        if (input.toUpperCase().startsWith("PRIVMSG")) {
            List<String> tokens = Arrays.asList(input.split(" "));
            String content = TextUtils.join(" ", tokens.subList(2, tokens.size()));
            if (content.startsWith(":")) content = content.substring(1);
            Message msg = new Message(content,
                    clientUser,
                    new Date(),
                    new Channel(tokens.get(1))
            );
            activity.addMessage(msg);
        }
        this.thread.write(input);
    }

    public Channel getChannel() {
        return channel;
    }

    public int getPort() {
        return port;
    }

    public IRCThread getThread() {
        return thread;
    }

    public String getServer() {
        return server;
    }

    public String getUsername() {
        return username + (Globals.usernameSuffix >= 0 ? Globals.usernameSuffix : "");
    }

    public String getSuppliedUsername() {
        return username;
    }

    public User getClientUser() {
        return clientUser;
    }

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }
}
