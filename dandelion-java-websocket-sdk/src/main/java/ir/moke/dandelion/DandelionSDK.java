package ir.moke.dandelion;

import ir.moke.dandelion.model.Credential;
import ir.moke.dandelion.websocket.MessageListenerHandler;
import ir.moke.dandelion.websocket.MessageListener;
import ir.moke.dandelion.websocket.WebSocketClient;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

public class DandelionSDK {
    private WebSocketContainer container;
    private Session session;
    private String endpoint;
    private String url;

    public DandelionSDK(String apiKey) {
        this.container = ContainerProvider.getWebSocketContainer();
        Credential credential = DandelionCredentialFactory.getCredential();
        endpoint = "ws://localhost:8080/channel/";
        url = endpoint + apiKey + "/" + credential.getDeviceId();
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void registerMessageListener(Class<? extends MessageListener> messageListener) {
        MessageListenerHandler.instance.registerMessageListener(messageListener);
    }

    public void start() {
        DandelionCredentialFactory.initialize();
        try {
            session = container.connectToServer(WebSocketClient.class, new URI(url));
            while (session.isOpen()) {

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            DandelionCredentialFactory.destroyToken();
        }
    }

    public void stop() {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
