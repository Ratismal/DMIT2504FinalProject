package stupidcat.me.finalproject.Structures.Event;

import java.util.ArrayList;
import java.util.List;

import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-10.
 */

public class UserListEvent extends BaseEvent {
    List<String> userList;

    public UserListEvent(IRCEvent e) {
        super(e);
        userList = new ArrayList<>();
        for (String name : e.getTokens().get(3).split(" ")) {
            userList.add(name);
        }
    }

    public List<String> getUserList() {
        return userList;
    }

    public Channel getChannel() {
        return new Channel(e.getTokens().get(2));
    }
}
