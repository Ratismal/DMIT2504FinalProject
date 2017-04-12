package stupidcat.me.finalproject.Structures;

import java.util.Date;

/**
 * Created by cat on 2017-04-05.
 */

public class Message {

    private String content;
    private User author;
    private Date timestamp;
    private Channel channel;

    private boolean priv = false;
    private MessageType type = MessageType.REGULAR;
    private boolean italic = false;

    public Message(String content, User author, Date timestamp, Channel channel, MessageType type) {
        this(content, author, timestamp, channel);
        this.type = type;
    }

    public Message(String content, User author, Date timestamp, Channel channel) {
        this(content, author, timestamp);
        this.priv = false;
        this.channel = channel;
    }

    public Message(String content, User author, Date timestamp) {
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.priv = true;

        if (this.content.startsWith("\u0001ACTION ") && this.content.endsWith("\u0001")) {
            this.content = "* " + this.author.getName() + " " + this.content.substring(8, this.content.length() - 1);
            this.italic = true;
        }
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Channel getChannel() {
        return channel;
    }

    public boolean isPrivate() {
        return priv;
    }

    public MessageType getType() {
        return type;
    }

    public boolean isItalic() {
        return italic;
    }
}
