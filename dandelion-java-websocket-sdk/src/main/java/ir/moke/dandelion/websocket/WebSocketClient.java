package ir.moke.dandelion.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.Observable;

@ClientEndpoint (configurator = WebSocketConfiguration.class)
public class WebSocketClient extends Observable {

    @OnOpen
    public void onOpen(Session session) {
        addObserver(new MessageListener());
    }

    @OnMessage
    public void onMessage(String msg) {
        setChanged();
        notifyObservers(msg);
    }
}
