package ir.moke.dandelion;

import ir.moke.dandelion.web.WebSocketCredentialHandler;
import ir.moke.dandelion.websocket.WebSocketClient;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Properties;

public class MainClass {


    public static void main(String[] args) throws URISyntaxException, IOException, DeploymentException {
        WebSocketCredentialHandler webSocketCredentialHandler = new WebSocketCredentialHandler();
        webSocketCredentialHandler.initialize();
        Properties properties = ClientConfig.getProperties();
        String apiKey = "5a5898a0-3212-4898-9e99-f926827bc5cc";
        String url = "ws://localhost:8080/channel/" + apiKey + "/" + properties.getProperty("deviceId");

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = null;
        try {
            session = container.connectToServer(WebSocketClient.class, new URI(url));
        } catch (Exception e) {
            webSocketCredentialHandler.initialize();
        }
        while (session.isOpen()) {
        }
    }

}
