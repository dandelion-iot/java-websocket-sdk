package ir.moke.dandelion.websocket;

import ir.moke.dandelion.logger.LoggerProducer;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Logger;

@ClientEndpoint(configurator = WebSocketConfiguration.class)
public class WebSocketClient {

    private static final Logger logger = LoggerProducer.produceLogger();

    @OnOpen
    public void onOpen(Session session) {
        StatusHandler.instance.setStatus(1);
        logger.info("Connected to server .");
    }

    @OnMessage
    public void onMessage(String msg) throws IOException {
        MessageListenerHandler.instance.notifyListener(msg);
    }

    @OnError
    public void onError(Session session, Throwable t) throws IOException {
        System.out.println("ERROR");
        t.printStackTrace();
        session.close();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) throws IOException {
        int code = closeReason.getCloseCode().getCode();
        StatusHandler.instance.setStatus(code);
        session.close();
    }
}
