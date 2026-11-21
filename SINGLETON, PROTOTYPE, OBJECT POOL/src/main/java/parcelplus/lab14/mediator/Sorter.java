package parcelplus.lab14.mediator;

import parcelplus.Log;

public class Sorter {

    public void route(String parcelId, String destination) {
        Log.log("Sorter", "Parcel " + parcelId + " directed to the " + destination + " direction");
    }
}
