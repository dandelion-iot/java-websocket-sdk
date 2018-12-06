package ir.moke.dandelion.websocket;

public class StatusHandler implements ConnectionStatus {

    public static StatusHandler instance = new StatusHandler();
    private StatusListener sl;

    private StatusHandler() {

    }

    @Override
    public void registerStatusListener(StatusListener sl) {
        this.sl = sl;
    }

    @Override
    public void setStatus(int status) {
        sl.onStatus(status);
    }
}
