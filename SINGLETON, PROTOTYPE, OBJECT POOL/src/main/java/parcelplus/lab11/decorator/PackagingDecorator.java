package parcelplus.lab11.decorator;

public class PackagingDecorator extends ShipmentDecorator {
    public PackagingDecorator(Shipment wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "packaging";
    }

    @Override
    protected double surcharge() {
        return 25;
    }
}
