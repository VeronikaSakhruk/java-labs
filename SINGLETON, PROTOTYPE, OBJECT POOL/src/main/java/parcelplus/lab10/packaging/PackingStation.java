package parcelplus.lab10.packaging;

import parcelplus.Log;

public class PackingStation {

    public void pack(String shipmentId, PackagingKitFactory factory) {
        Log.log("PackingStation", "Packing shipment " + shipmentId);
        Box box = factory.createBox();
        Filler filler = factory.createFiller();
        Label label = factory.createLabel();
        Log.log("PackingStation", "Box: " + box.describe() + " [" + box.type() + "]");
        Log.log("PackingStation", "Filler: " + filler.describe() + " [" + filler.type() + "]");
        Log.log("PackingStation", "Label: " + label.describe() + " [" + label.type() + "]");
        boolean sameType = box.type().equals(filler.type()) && box.type().equals(label.type());
        Log.log("PackingStation", "All elements belong to one type: " + sameType + " (" + box.type() + ")");
        Log.log("PackingStation", "Shipment " + shipmentId + " packed");
    }
}
