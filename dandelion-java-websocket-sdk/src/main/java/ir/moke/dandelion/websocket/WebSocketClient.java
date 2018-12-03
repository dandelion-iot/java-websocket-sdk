package ir.moke.dandelion.websocket;

import ir.moke.dandelion.DandelionCredentialFactory;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint(configurator = WebSocketConfiguration.class)
public class WebSocketClient {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket opened ....");
    }

    @OnMessage
    public void onMessage(String msg)  {
        if (msg.equals("#000001")) {
            DandelionCredentialFactory.destroyToken();
        } else {
            MessageListenerHandler.instance.notifyListener(msg);
        }
    }
}
