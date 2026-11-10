package parcelplus.lab11.decorator;

public class SmsNotificationDecorator extends ShipmentDecorator {
    public SmsNotificationDecorator(Shipment wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "SMS notification";
    }

    @Override
    protected double surcharge() {
        return 10;
    }
}
