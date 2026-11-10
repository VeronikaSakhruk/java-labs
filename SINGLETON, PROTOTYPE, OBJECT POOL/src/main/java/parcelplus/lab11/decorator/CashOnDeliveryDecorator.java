package parcelplus.lab11.decorator;

public class CashOnDeliveryDecorator extends ShipmentDecorator {
    public CashOnDeliveryDecorator(Shipment wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "cash on delivery";
    }

    @Override
    protected double surcharge() {
        return 30;
    }
}
