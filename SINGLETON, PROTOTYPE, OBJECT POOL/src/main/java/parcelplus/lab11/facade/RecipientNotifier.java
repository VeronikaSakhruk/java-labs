package parcelplus.lab11.facade;

import parcelplus.Log;

public class RecipientNotifier {
    public void notifyRecipient(String recipient, String waybill) {
        Log.log("RecipientNotifier", recipient + " notified about waybill " + waybill);
    }
}
