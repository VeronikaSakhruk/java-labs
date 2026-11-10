package parcelplus.lab11.decorator;

public class InsuranceDecorator extends ShipmentDecorator {
    public InsuranceDecorator(Shipment wrapped) {
        super(wrapped);
    }

    @Override
    protected String label() {
        return "insurance";
    }

    @Override
    protected double surcharge() {
        return 40;
    }
}
