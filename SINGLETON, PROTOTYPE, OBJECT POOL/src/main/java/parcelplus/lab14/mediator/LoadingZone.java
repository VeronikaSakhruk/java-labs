package parcelplus.lab14.mediator;

import parcelplus.Log;

public class LoadingZone {

    private final SortingMediator mediator;

    public LoadingZone(SortingMediator mediator) {
        this.mediator = mediator;
    }

    public void requestLoading(String bagId, String destination) {
        Log.log("LoadingZone", "Bag " + bagId + " for " + destination + " is ready, asking coordinator");
        mediator.loadingRequested(bagId, destination);
    }

    public void truckArrives(String truckId, String destination) {
        Log.log("LoadingZone", "Truck " + truckId + " for " + destination + " arrived, reporting to coordinator");
        mediator.truckArrived(truckId, destination);
    }

    public void startLoading(String bagId, String truckId) {
        Log.log("LoadingZone", "Loading of bag " + bagId + " into truck " + truckId + " started");
    }
}
