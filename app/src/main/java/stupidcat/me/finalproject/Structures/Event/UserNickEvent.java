package stupidcat.me.finalproject.Structures.Event;

import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-10.
 */

public class UserNickEvent extends BaseEvent {

    String oldName;
    String newName;

    public UserNickEvent(IRCEvent e) {
        super(e);
        oldName = e.getUser().getName();
        newName = e.getTokens().get(0);
        e.getUser().setName(newName);
    }

    public User getUser() {
        return e.getUser();
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }
}
