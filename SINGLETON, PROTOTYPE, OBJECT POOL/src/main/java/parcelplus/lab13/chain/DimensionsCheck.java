package parcelplus.lab13.chain;

public class DimensionsCheck extends ShipmentCheck {

    private static final int MAX_SIDE_CM = 120;

    @Override
    protected String validate(Shipment shipment) {
        int longest = Math.max(shipment.lengthCm(), Math.max(shipment.widthCm(), shipment.heightCm()));
        if (longest > MAX_SIDE_CM) {
            return "longest side " + longest + " cm exceeds the limit of " + MAX_SIDE_CM + " cm";
        }
        return null;
    }
}
