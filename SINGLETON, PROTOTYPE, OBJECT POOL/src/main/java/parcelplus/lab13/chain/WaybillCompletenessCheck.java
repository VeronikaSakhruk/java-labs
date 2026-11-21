package parcelplus.lab13.chain;

public class WaybillCompletenessCheck extends ShipmentCheck {

    @Override
    protected String validate(Shipment shipment) {
        if (shipment.sender() == null || shipment.sender().isBlank()) {
            return "sender is not specified";
        }
        if (shipment.receiver() == null || shipment.receiver().isBlank()) {
            return "receiver is not specified";
        }
        if (shipment.contents() == null || shipment.contents().isBlank()) {
            return "contents are not described";
        }
        return null;
    }
}
