package ir.moke.dandelion.websocket;

import java.util.Observable;
import java.util.Observer;

public abstract class MessageListener implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        onReceiveMessage((String) arg);
    }

    public abstract void onReceiveMessage(String message);
}
