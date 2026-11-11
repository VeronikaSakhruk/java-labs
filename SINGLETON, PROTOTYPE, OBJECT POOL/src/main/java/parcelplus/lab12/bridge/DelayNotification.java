package parcelplus.lab12.bridge;

public class DelayNotification extends Notification {

    private final int minutes;

    public DelayNotification(Channel channel, int minutes) {
        super(channel);
        this.minutes = minutes;
    }

    @Override
    protected String text() {
        return "Delivery of your parcel is delayed by " + minutes + " minutes";
    }
}
