package parcelplus.lab14.observer;

import parcelplus.Log;

public class OnlineStore implements StatusObserver {

    @Override
    public void onStatusChanged(String shipmentId, ShipmentStatus status) {
        Log.log("OnlineStore", "Store API called: order of shipment " + shipmentId + " is " + status);
    }
}
