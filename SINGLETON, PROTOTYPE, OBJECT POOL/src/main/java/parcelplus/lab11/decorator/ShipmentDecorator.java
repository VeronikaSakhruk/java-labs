package parcelplus.lab11.decorator;

import parcelplus.Log;

public abstract class ShipmentDecorator implements Shipment {
    private final Shipment wrapped;

    protected ShipmentDecorator(Shipment wrapped) {
        this.wrapped = wrapped;
    }

    protected abstract String label();

    protected abstract double surcharge();

    @Override
    public String description() {
        return wrapped.description() + " + " + label();
    }

    @Override
    public double cost() {
        double inner = wrapped.cost();
        double total = inner + surcharge();
        Log.log(getClass().getSimpleName(), label() + " adds " + Log.fmt(surcharge())
                + " UAH to " + Log.fmt(inner) + ", total " + Log.fmt(total) + " UAH");
        return total;
    }
}
