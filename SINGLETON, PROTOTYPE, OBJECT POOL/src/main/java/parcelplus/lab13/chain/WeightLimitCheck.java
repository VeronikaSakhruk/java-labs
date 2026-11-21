package parcelplus.lab13.chain;

import parcelplus.Log;

public class WeightLimitCheck extends ShipmentCheck {

    private static final double MAX_WEIGHT_KG = 30;

    @Override
    protected String validate(Shipment shipment) {
        if (shipment.weightKg() > MAX_WEIGHT_KG) {
            return "weight " + Log.fmt(shipment.weightKg()) + " kg exceeds the limit of "
                    + Log.fmt(MAX_WEIGHT_KG) + " kg";
        }
        return null;
    }
}
