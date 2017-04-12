package stupidcat.me.finalproject.Structures;

/**
 * Created by cat on 2017-04-05.
 */

public class User {

    String name;
    String host;

    public User(String name, String host) {
        this.name = name;
        this.host = host;
        if (this.name.startsWith(":")) {
            this.name = this.name.substring(1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public String getFullName() {
        return name + " (" + host + ")";
    }
}
