package stupidcat.me.finalproject.Network;

import android.text.TextUtils;

import stupidcat.me.finalproject.Activities.ChannelActivity;
import stupidcat.me.finalproject.Constants.Defaults;
import stupidcat.me.finalproject.Constants.Globals;
import stupidcat.me.finalproject.Structures.Event.UserJoinEvent;
import stupidcat.me.finalproject.Structures.Event.UserListEvent;
import stupidcat.me.finalproject.Structures.Event.UserNickEvent;
import stupidcat.me.finalproject.Structures.Event.UserPartEvent;
import stupidcat.me.finalproject.Structures.Event.IRCEvent;
import stupidcat.me.finalproject.Structures.Event.MessageEvent;
import stupidcat.me.finalproject.Structures.Event.PingEvent;
import stupidcat.me.finalproject.Structures.Event.UserNameInUseEvent;
import stupidcat.me.finalproject.Structures.Event.UserQuitEvent;
import stupidcat.me.finalproject.Structures.IRCEventListener;
import stupidcat.me.finalproject.Structures.MessageType;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-05.
 */

public class IRCListener implements IRCEventListener {

    private IRCClient client;
    private ChannelActivity activity;

    IRCListener(IRCClient client, ChannelActivity activity) {
        this.client = client;
        this.activity = activity;
    }

    @Override
    public void onMessage(MessageEvent e) {
        if (e.getMsg().isPrivate()) onPrivateMessage(e);
        else onChannelMessage(e);
    }

    @Override
    public void onPrivateMessage(MessageEvent e) {

    }

    @Override
    public void onChannelMessage(MessageEvent e) {
        if (e.getMsg().getChannel().getName().equals(activity.getChannel())) {
            activity.addMessage(e.getMsg());
        }
    }

    @Override
    public void onPing(PingEvent e) {
        client.sendRaw("PONG :" + e.getCode());
    }

    @Override
    public void onUserNameInUse(UserNameInUseEvent e) {
        Globals.usernameSuffix++;
        activity.addSystemMessage("Your username is in use, renaming to " + client.getUsername(), MessageType.ERROR);
        client.sendRaw("NICK " + client.getUsername());
    }

    @Override
    public void onJoin(UserJoinEvent e) {
        activity.addSystemMessage(e.getUser().getFullName() + " has joined " + e.getChannel().getName());
    }

    @Override
    public void onKick(UserPartEvent e) {
        activity.addSystemMessage(e.getUser().getFullName() + " has been kicked from "
                + e.getChannel().getName() + " (Reason: " + e.getReason() + ")");
    }

    @Override
    public void onPart(UserPartEvent e) {
        activity.addSystemMessage(e.getUser().getFullName() + " has left "
                + e.getChannel().getName() + " (Reason: " + e.getReason() + ")");
    }

    @Override
    public void onQuit(UserQuitEvent e) {
        activity.addSystemMessage(e.getUser().getFullName() + " has disconnected (Reason: "
                + e.getReason() + ")");
    }

    public void onNickChange(UserNickEvent e) {
        activity.addSystemMessage(e.getOldName() + "(" + e.getUser().getHost() + ") is now known as " + e.getNewName());
    }

    @Override
    public void onUserList(UserListEvent e) {
        activity.addSystemMessage("Users in " + e.getChannel().getName() + ":\n"
                + " - " + TextUtils.join("\n - ", e.getUserList()));
    }

    @Override
    public void onRaw(IRCEvent e) {
        switch (e.getCommand()) {
            case "PING":
                onPing(new PingEvent(e));
                break;
            case "PRIVMSG":
                MessageEvent messageEvent = new MessageEvent(e);
                onMessage(messageEvent);
                break;
            case "376":
                onReady();
                break;
            case "433":
                onUserNameInUse(new UserNameInUseEvent(e));
                break;
            case "353":
                onUserList(new UserListEvent(e));
                break;
            case "JOIN":
                onJoin(new UserJoinEvent(e));
                break;
            case "PART":
                onPart(new UserPartEvent(e));
                break;
            case "KICK":
                onKick(new UserPartEvent(e));
                break;
            case "QUIT":
                onQuit(new UserQuitEvent(e));
                break;
            case "NICK":
                onNickChange(new UserNickEvent(e));
                break;
        }
    }

    @Override
    public void onInitialized() {
        activity.addSystemMessage("Connecting...");
        System.out.println("IRC Init");
        client.sendRaw("NICK " + client.getUsername());
        client.sendRaw("USER " + client.getUsername() + " 8 * : An IRC client for DMIT2504");
    }

    @Override
    public void onReady() {
        client.setClientUser(new User(client.getUsername(), "!"));
        activity.addSystemMessage("Connected. Do '/help' for a list of commands.");
        System.out.println("Successfully connected!");
        client.sendRaw("JOIN " + Defaults.DEFAULT_CHANNEL);
    }
}
