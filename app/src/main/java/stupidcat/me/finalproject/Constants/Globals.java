package stupidcat.me.finalproject.Constants;

import java.util.ArrayList;
import java.util.HashMap;

import stupidcat.me.finalproject.Activities.ChannelActivity;
import stupidcat.me.finalproject.Network.IRCClient;

/**
 * Created by cat on 2017-04-05.
 */

public class Globals {

    public static IRCClient ircClient;

    public static int usernameSuffix = 0;

    public static HashMap<String, ChannelActivity> channelActivities = new HashMap<>();
    public static ArrayList<String> channels = new ArrayList<>();
}
