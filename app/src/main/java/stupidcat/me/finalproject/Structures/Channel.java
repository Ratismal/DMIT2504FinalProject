package stupidcat.me.finalproject.Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cat on 2017-04-05.
 */

public class Channel {

    String name = "";
    List<User> users = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}
