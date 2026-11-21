package parcelplus.lab13.chain;

import java.util.List;

public class ProhibitedItemsCheck extends ShipmentCheck {

    private static final List<String> PROHIBITED = List.of("explosive", "weapon", "flammable liquid");

    @Override
    protected String validate(Shipment shipment) {
        String contents = shipment.contents().toLowerCase();
        for (String item : PROHIBITED) {
            if (contents.contains(item)) {
                return "prohibited item: " + item;
            }
        }
        return null;
    }
}
