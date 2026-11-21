package parcelplus.lab14.mediator;

import parcelplus.Log;

public class BarcodeScanner {

    private final SortingMediator mediator;

    public BarcodeScanner(SortingMediator mediator) {
        this.mediator = mediator;
    }

    public void scan(String parcelId, String destination) {
        Log.log("BarcodeScanner", "Scanned " + parcelId + ", destination " + destination + ", reporting to coordinator");
        mediator.parcelScanned(parcelId, destination);
    }
}
