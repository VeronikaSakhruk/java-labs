package parcelplus.lab14.observer;

import parcelplus.Log;

public class Sender implements StatusObserver {

    private final String name;

    public Sender(String name) {
        this.name = name;
    }

    @Override
    public void onStatusChanged(String shipmentId, ShipmentStatus status) {
        Log.log("Sender", "SMS to " + name + ": shipment " + shipmentId + " is now " + status);
    }
}
