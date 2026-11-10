package parcelplus.lab11.decorator;

import parcelplus.Log;

public class BasicShipment implements Shipment {
    private final String id;
    private final double weightKg;

    public BasicShipment(String id, double weightKg) {
        this.id = id;
        this.weightKg = weightKg;
    }

    @Override
    public String description() {
        return "Shipment " + id + " (" + weightKg + " kg)";
    }

    @Override
    public double cost() {
        double cost = 50 + 12 * weightKg;
        Log.log("BasicShipment", "Base cost of " + id + ": " + Log.fmt(cost) + " UAH");
        return cost;
    }
}
