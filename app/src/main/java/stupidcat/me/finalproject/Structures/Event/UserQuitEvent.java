package stupidcat.me.finalproject.Structures.Event;

import android.text.TextUtils;

import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-10.
 */

public class UserQuitEvent extends BaseEvent {

    String reason;

    public UserQuitEvent(IRCEvent e) {
        super(e);
        reason = e.getTokens().get(0);
    }

    public User getUser() {
        return e.getUser();
    }

    public String getReason() {
        return reason;
    }
}
