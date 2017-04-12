package stupidcat.me.finalproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import stupidcat.me.finalproject.R;
import stupidcat.me.finalproject.Structures.Message;

/**
 * Created by cat on 2017-04-08.
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, ArrayList<Message> drafts) {
        super(context, 0, drafts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.channel_view_item, parent, false);
        }

        TextView sender = (TextView) convertView.findViewById(R.id.ChannelViewItem_author);
        TextView timestamp = (TextView) convertView.findViewById(R.id.ChannelViewItem_timestamp);
        TextView content = (TextView) convertView.findViewById(R.id.ChannelViewItem_content);
        int background = 0xFFFFFF;

        switch (message.getType()) {
            case REGULAR:
                background = convertView.getResources().getColor(R.color.messageRegular);
                break;
            case SYSTEM:
                background = convertView.getResources().getColor(R.color.messageSystem);
                break;
            case ERROR:
                background = convertView.getResources().getColor(R.color.messageError);
                break;
        }

        convertView.setBackgroundColor(background);

        sender.setText(message.getAuthor().getName());
        timestamp.setText(new SimpleDateFormat("hh:mm:ss a", Locale.CANADA).format(message.getTimestamp()));
        content.setText(message.getContent());
        if (message.isItalic()) {
            content.setTypeface(null, Typeface.ITALIC);
        }
        return convertView;
    }
}
