package stupidcat.me.finalproject.Structures.Event;

import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-10.
 */

public class UserJoinEvent extends BaseEvent {

    public UserJoinEvent(IRCEvent e) {
        super(e);
    }

    public User getUser() {
        return e.getUser();
    }

    public Channel getChannel() {
        return new Channel(e.getTokens().get(0));
    }
}
