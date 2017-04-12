package stupidcat.me.finalproject.Structures.Event;

import java.util.Date;

import stupidcat.me.finalproject.Structures.Channel;
import stupidcat.me.finalproject.Structures.Message;

/**
 * Created by cat on 2017-04-05.
 */

public class MessageEvent extends BaseEvent {

    private Message msg;

    public MessageEvent(IRCEvent e) {
        super(e);
        Date timestamp = new Date();
        System.out.println(e.getTokens().get(0) + " " + e.getTokens().get(0).startsWith("#"));
        if (e.getTokens().get(0).startsWith("#")) {
            Channel channel = new Channel(e.getTokens().get(0));
            this.msg = new Message(e.getTokens().get(1), e.getUser(), timestamp, channel);
        } else {
            this.msg = new Message(e.getTokens().get(1), e.getUser(), timestamp);
        }
    }

    public Message getMsg() {
        return msg;
    }
}
