package parcelplus.lab14.observer;

import parcelplus.Log;

public class OnlineCabinet implements StatusObserver {

    @Override
    public void onStatusChanged(String shipmentId, ShipmentStatus status) {
        Log.log("OnlineCabinet", "Tracking page of " + shipmentId + " updated: " + status);
    }
}
