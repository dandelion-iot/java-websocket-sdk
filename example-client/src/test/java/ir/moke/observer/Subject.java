package ir.moke.observer;

public interface Subject {
    void registerListener(MessageListener o);
    void notifyListener() ;
}
