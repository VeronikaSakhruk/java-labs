package parcelplus.lab14.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import parcelplus.Log;

public class Shipment {

    private final String id;
    private ShipmentStatus status = ShipmentStatus.ACCEPTED;
    private final List<StatusObserver> observers = new CopyOnWriteArrayList<>();

    public Shipment(String id) {
        this.id = id;
    }

    public void subscribe(StatusObserver observer) {
        observers.add(observer);
        Log.log("Shipment " + id, observer.getClass().getSimpleName() + " subscribed ("
                + observers.size() + " subscribers)");
    }

    public void unsubscribe(StatusObserver observer) {
        observers.remove(observer);
        Log.log("Shipment " + id, observer.getClass().getSimpleName() + " unsubscribed ("
                + observers.size() + " subscribers)");
    }

    public void changeStatus(ShipmentStatus newStatus) {
        status = newStatus;
        Log.log("Shipment " + id, "Status changed to " + newStatus + ", notifying "
                + observers.size() + " subscribers");
        for (StatusObserver observer : observers) {
            try {
                observer.onStatusChanged(id, newStatus);
            } catch (RuntimeException e) {
                Log.log("Shipment " + id, observer.getClass().getSimpleName() + " failed: " + e.getMessage());
            }
        }
    }
}
