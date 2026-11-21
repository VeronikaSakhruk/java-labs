package parcelplus.lab13.chain;

public class CustomsDeclarationCheck extends ShipmentCheck {

    @Override
    protected String validate(Shipment shipment) {
        if (shipment.international()
                && (shipment.customsDeclaration() == null || shipment.customsDeclaration().isBlank())) {
            return "customs declaration is missing";
        }
        return null;
    }
}
