package stupidcat.me.finalproject.Structures;

import stupidcat.me.finalproject.Structures.Event.UserJoinEvent;
import stupidcat.me.finalproject.Structures.Event.UserListEvent;
import stupidcat.me.finalproject.Structures.Event.UserNickEvent;
import stupidcat.me.finalproject.Structures.Event.UserPartEvent;
import stupidcat.me.finalproject.Structures.Event.IRCEvent;
import stupidcat.me.finalproject.Structures.Event.MessageEvent;
import stupidcat.me.finalproject.Structures.Event.PingEvent;
import stupidcat.me.finalproject.Structures.Event.UserNameInUseEvent;
import stupidcat.me.finalproject.Structures.Event.UserQuitEvent;

/**
 * Created by cat on 2017-04-05.
 */

public abstract interface IRCEventListener {

    void onMessage(MessageEvent e);
    void onPrivateMessage(MessageEvent e);
    void onChannelMessage(MessageEvent e);
    void onUserNameInUse(UserNameInUseEvent e);
    void onJoin(UserJoinEvent e);
    void onQuit(UserQuitEvent e);
    void onPart(UserPartEvent e);
    void onKick(UserPartEvent e);
    void onNickChange(UserNickEvent e);
    void onUserList(UserListEvent e);

    void onPing(PingEvent e);

    void onInitialized();
    void onReady();

    void onRaw(IRCEvent e);

}
