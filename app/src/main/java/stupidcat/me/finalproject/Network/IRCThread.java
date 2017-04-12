package stupidcat.me.finalproject.Network;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import stupidcat.me.finalproject.Structures.Event.IRCEvent;
import stupidcat.me.finalproject.Structures.IRCEventListener;

/**
 * Created by cat on 2017-04-05.
 */

public class IRCThread extends Thread {

    private String server;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private IRCEventListener listener;
    private BlockingQueue<String> queue;

    IRCThread(String server, int port, IRCEventListener listener, BlockingQueue<String> q) {
        System.out.println("Creating an IRC Thread");
        this.server = server;
        this.port = port;
        this.listener = listener;
        this.queue = q;
    }

    public void run() {
        System.out.println("Running the IRC Thread");
        try {
            this.socket = new Socket(this.server, this.port);

            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = null;
            String input = null;
            listener.onInitialized();
            while (true) {
                while ((input = queue.poll()) != null) {
                    write(input);
                }
                while ((line = reader.readLine()) != null) {
                    listener.onRaw(new IRCEvent(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void write(String message) {
        final String msg = message;
        (new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Sending message: " + msg);
                    writer.write(msg + "\r\n");
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
