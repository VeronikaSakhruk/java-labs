package parcelplus.lab14.observer;

import parcelplus.Log;

public class Receiver implements StatusObserver {

    private final String name;

    public Receiver(String name) {
        this.name = name;
    }

    @Override
    public void onStatusChanged(String shipmentId, ShipmentStatus status) {
        Log.log("Receiver", "Push notification to " + name + ": your parcel " + shipmentId + " is " + status);
    }
}
