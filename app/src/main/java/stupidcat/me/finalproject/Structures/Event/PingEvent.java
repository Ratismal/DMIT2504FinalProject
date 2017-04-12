package stupidcat.me.finalproject.Structures.Event;

/**
 * Created by cat on 2017-04-05.
 */

public class PingEvent extends BaseEvent{

    private String code;

    public PingEvent(IRCEvent e) {
        super(e);
        this.code = e.getTokens().get(0);
    }

    public String getCode() {
        return code;
    }
}
