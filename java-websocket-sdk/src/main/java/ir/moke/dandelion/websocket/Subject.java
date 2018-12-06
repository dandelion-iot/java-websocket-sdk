package ir.moke.dandelion.websocket;

public interface Subject {
    void registerMessageListener(Class<? extends MessageListener> listenerClass) ;
    void notifyListener(String message) ;
}
