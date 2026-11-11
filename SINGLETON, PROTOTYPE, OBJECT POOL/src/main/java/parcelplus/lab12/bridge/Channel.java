package parcelplus.lab12.bridge;

public interface Channel {

    String name();

    void deliver(String recipient, String text);
}
