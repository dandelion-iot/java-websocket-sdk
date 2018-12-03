package ir.moke.dandelion;

import ir.moke.dandelion.websocket.MessageListener;

public class SampleMessageListener implements MessageListener {
    @Override
    public void onMessage(String message) {
        System.out.println("Received : " + message);
    }
}
