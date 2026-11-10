package parcelplus.lab11.facade;

import parcelplus.Log;

public class RoutePlanner {
    public void plan(String waybill, String address) {
        Log.log("RoutePlanner", "Waybill " + waybill + " added to the route to " + address);
    }
}
