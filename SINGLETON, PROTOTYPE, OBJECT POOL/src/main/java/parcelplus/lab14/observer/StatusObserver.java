package parcelplus.lab14.observer;

public interface StatusObserver {

    void onStatusChanged(String shipmentId, ShipmentStatus status);
}
