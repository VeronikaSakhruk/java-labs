package parcelplus.lab11.decorator;

public class WeekendDeliveryDecorator extends ShipmentDecorator {
    public WeekendDeliveryDecorator(Shipment wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "weekend delivery";
    }

    @Override
    protected double surcharge() {
        return 60;
    }
}
