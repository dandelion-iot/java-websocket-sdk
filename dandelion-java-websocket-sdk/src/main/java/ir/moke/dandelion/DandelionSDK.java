package ir.moke.dandelion;

import ir.moke.dandelion.logger.LoggerProducer;
import ir.moke.dandelion.model.Credential;
import ir.moke.dandelion.websocket.*;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class DandelionSDK implements StatusListener {
    private static final Logger logger = LoggerProducer.produceLogger();

    private WebSocketContainer container;
    private Session session;
    private String endpoint = "ws://localhost:8080/channel/";
    private String url;
    private String apiKey;
    private int status;

    public DandelionSDK(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void registerMessageListener(Class<? extends MessageListener> messageListener) {
        MessageListenerHandler.instance.registerMessageListener(messageListener);
    }

    private void registerStatusHandler() {
        StatusHandler.instance.registerStatusListener(this);
    }

    public void init() throws Exception {
        DandelionCredentialFactory.initialize();
        this.container = ContainerProvider.getWebSocketContainer();
        Credential credential = DandelionCredentialFactory.getCredential();
        url = endpoint + apiKey + "/" + credential.getDeviceId();
    }

    public void start() {
        registerStatusHandler();
        while (true) {
            try {
                init();
                session = container.connectToServer(WebSocketClient.class, new URI(url));
                if (status == 1) {
                    logger.fine("Connection established .");
                    while (session.isOpen()) {}
                } else if (status == 1006) {
                    session.close();
                }
            } catch (Exception e) {
                logger.fine("Handshake error , please contact to your administrator .");
                Credential credential = DandelionCredentialFactory.getCredential();
                logger.fine("API key : " + apiKey);
                logger.fine("DeviceID : " + credential.getDeviceId());
                logger.fine("Token : " + credential.getToken());
                DandelionCredentialFactory.destroyToken();
                sleep(10000);
            }
        }
    }

    public void stop() {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatus(int status) {
        this.status = status;
    }

    private void sleep (int milSec) {
        try {
            Thread.sleep(milSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
