package ir.moke.observer;

public class TestObserverPattern {
    public static void main(String[] args) {
        MyMessageListener myMessageListener = new MyMessageListener() ;

        Message message = new Message() ;
        message.setMessageListener(myMessageListener);
        message.setReceived(true);
    }
}
