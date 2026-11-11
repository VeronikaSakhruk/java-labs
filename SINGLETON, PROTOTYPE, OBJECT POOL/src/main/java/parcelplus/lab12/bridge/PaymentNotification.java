package parcelplus.lab12.bridge;

import parcelplus.Log;

public class PaymentNotification extends Notification {

    private final double amount;

    public PaymentNotification(Channel channel, double amount) {
        super(channel);
        this.amount = amount;
    }

    @Override
    protected String text() {
        return "Payment of " + Log.fmt(amount) + " UAH is required to receive your parcel";
    }
}
