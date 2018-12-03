package ir.moke.observer;

public class Message implements Subject {
    private MessageListener messageListener;

    private boolean received ;

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
        if (isReceived()) {
            notifyListener();
        }
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void registerListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void notifyListener() {
        messageListener.onMessage("salam mahdi");
    }
}
