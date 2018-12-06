package ir.moke.dandelion.websocket;

public interface ConnectionStatus {
    void registerStatusListener(StatusListener sl);
    void setStatus(int status) ;
}
