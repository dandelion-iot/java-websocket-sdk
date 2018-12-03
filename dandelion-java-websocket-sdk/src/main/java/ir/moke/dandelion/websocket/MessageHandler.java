package ir.moke.dandelion.websocket;

public class MessageHandler implements Subject {
    public static MessageHandler instance = new MessageHandler();
    private MessageListener messageListener;

    private MessageHandler() {
    }

    @Override
    public void registerMessageListener(Class<? extends MessageListener> listenerClass) {
        try {
            this.messageListener = listenerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyListener(String message) {
        messageListener.onMessage(message);
    }
}
