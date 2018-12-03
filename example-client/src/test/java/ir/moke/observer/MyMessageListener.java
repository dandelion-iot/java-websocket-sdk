package ir.moke.observer;

public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(String message) {
        System.out.println("Receive : " + message);
    }
}
