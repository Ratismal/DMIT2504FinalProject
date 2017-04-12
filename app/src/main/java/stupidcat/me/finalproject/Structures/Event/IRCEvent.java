package stupidcat.me.finalproject.Structures.Event;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import stupidcat.me.finalproject.Structures.User;

/**
 * Created by cat on 2017-04-05.
 */

public class IRCEvent {

    private String raw;
    private String command;
    private List<String> tokens = new ArrayList<>();
    private String source;
    private String sourceHost;
    private User user;

    public IRCEvent(String data) {
        System.out.println(data);

        this.raw = data;

        String[] temp = this.raw.split(" ");
        String tempToken = "";
        boolean colonEnabled = false;
        for (int i = 0; i < temp.length; i++) {
            if (i == 0 && temp.length > 2) this.source = temp[i];
            else if ((i == 1 && temp.length > 2) || i == 0) this.command = temp[i];
            else {
                if (!colonEnabled) {
                    if (temp[i].startsWith(":")) {
                        tempToken += temp[i].substring(1);
                        colonEnabled = true;
                    } else {
                        tokens.add(temp[i]);
                    }
                } else {
                    tempToken += " " + temp[i];
                }
            }
        }
        if (!tempToken.equals("")) tokens.add(tempToken);

        if (this.source != null && this.source.contains("!")) {
            String[] tempSource = this.source.split("!");
            this.source = tempSource[0];
            this.sourceHost = tempSource[1];
            this.user = new User(this.source, this.sourceHost);
        }
    }

    public String getRaw() {
        return raw;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public String getCommand() {
        return command;
    }

    public String join(String separator) {
        return join(separator, 0, this.tokens.size());
    }

    public String join(String separator, int start) {
        return join(separator, start, this.tokens.size());
    }

    public String join(String separator, int start, int end) {
        return TextUtils.join(separator, this.tokens.subList(start, end));
    }

    public String getSource() {
        return source;
    }

    public String getSourceHost() {
        return sourceHost;
    }

    public User getUser() {
        return user;
    }
}
