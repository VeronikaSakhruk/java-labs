package parcelplus.lab11.proxy;

import parcelplus.Log;

public class PartnerTrackingService implements TrackingService {
    private static final String[] STATUSES = {
        "Accepted at partner sorting center",
        "Out for delivery",
        "Delivered"
    };

    private int paidRequests;

    @Override
    public String getStatus(String shipmentId) {
        paidRequests++;
        Log.log("PartnerTrackingService", "Paid request to partner for " + shipmentId
                + " (request #" + paidRequests + ")");
        return STATUSES[Math.min(paidRequests, STATUSES.length) - 1];
    }

    public int getPaidRequests() {
        return paidRequests;
    }
}
