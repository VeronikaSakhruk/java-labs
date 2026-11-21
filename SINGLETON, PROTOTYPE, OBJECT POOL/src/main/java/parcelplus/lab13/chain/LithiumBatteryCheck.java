package parcelplus.lab13.chain;

public class LithiumBatteryCheck extends ShipmentCheck {

    @Override
    protected String validate(Shipment shipment) {
        if (shipment.international() && shipment.lithiumBatteries()) {
            return "lithium batteries are not accepted for international shipments";
        }
        return null;
    }
}
