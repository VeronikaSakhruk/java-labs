package parcelplus.lab12.bridge;

import parcelplus.Log;

public abstract class Notification {

    private final Channel channel;

    protected Notification(Channel channel) {
        this.channel = channel;
    }

    protected abstract String text();

    public void send(String recipient) {
        Log.log(getClass().getSimpleName(), "Sending through " + channel.name());
        channel.deliver(recipient, text());
    }
}
