package parcelplus.lab14.mediator;

import java.util.HashMap;
import java.util.Map;
import parcelplus.Log;

public class SortingCenterController implements SortingMediator {

    private static final double MAX_WEIGHT_KG = 50;

    private IntakeBelt belt;
    private Sorter sorter;
    private LoadingZone loadingZone;
    private final Map<String, String> dockedTrucks = new HashMap<>();

    public void connect(IntakeBelt belt, Sorter sorter, LoadingZone loadingZone) {
        this.belt = belt;
        this.sorter = sorter;
        this.loadingZone = loadingZone;
        Log.log("SortingCenterController", "Belt, sorter and loading zone connected");
    }

    @Override
    public synchronized void parcelScanned(String parcelId, String destination) {
        Log.log("SortingCenterController", "Scanner event: " + parcelId + " -> sorter must route it to " + destination);
        sorter.route(parcelId, destination);
    }

    @Override
    public synchronized void weightMeasured(String parcelId, double weightKg) {
        if (weightKg > MAX_WEIGHT_KG) {
            Log.log("SortingCenterController", "Scale event: overload " + Log.fmt(weightKg) + " kg of "
                    + parcelId + " (limit " + Log.fmt(MAX_WEIGHT_KG) + " kg) -> stopping the belt");
            belt.stop();
        } else {
            Log.log("SortingCenterController", "Scale event: weight of " + parcelId + " is within the limit");
        }
    }

    @Override
    public synchronized void loadingRequested(String bagId, String destination) {
        String truckId = dockedTrucks.get(destination);
        if (truckId == null) {
            Log.log("SortingCenterController", "Loading zone event: no truck for " + destination
                    + " -> loading of " + bagId + " not allowed");
            return;
        }
        Log.log("SortingCenterController", "Loading zone event: truck " + truckId + " is present -> loading allowed");
        loadingZone.startLoading(bagId, truckId);
    }

    @Override
    public synchronized void truckArrived(String truckId, String destination) {
        dockedTrucks.put(destination, truckId);
        Log.log("SortingCenterController", "Loading zone event: truck " + truckId + " for " + destination
                + " is docked");
    }
}
