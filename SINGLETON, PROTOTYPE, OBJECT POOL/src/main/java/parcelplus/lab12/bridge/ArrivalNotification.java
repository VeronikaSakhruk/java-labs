package parcelplus.lab12.bridge;

public class ArrivalNotification extends Notification {

    private final String branch;

    public ArrivalNotification(Channel channel, String branch) {
        super(channel);
        this.branch = branch;
    }

    @Override
    protected String text() {
        return "Your parcel has arrived at " + branch;
    }
}
