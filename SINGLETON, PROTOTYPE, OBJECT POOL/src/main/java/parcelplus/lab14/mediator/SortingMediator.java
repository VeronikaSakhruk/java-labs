package parcelplus.lab14.mediator;

public interface SortingMediator {

    void parcelScanned(String parcelId, String destination);

    void weightMeasured(String parcelId, double weightKg);

    void loadingRequested(String bagId, String destination);

    void truckArrived(String truckId, String destination);
}
