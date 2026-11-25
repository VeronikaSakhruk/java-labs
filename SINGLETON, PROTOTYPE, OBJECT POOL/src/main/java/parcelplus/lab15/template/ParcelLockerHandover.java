package parcelplus.lab15.template;

import parcelplus.Log;

public class ParcelLockerHandover extends HandoverProcedure {

    @Override
    protected void identifyRecipient(Shipment shipment) {
        Log.log("ParcelLockerHandover", "One-time code of " + shipment.recipient() + " accepted by the locker");
    }

    @Override
    protected void confirmReceipt(Shipment shipment) {
        Log.log("ParcelLockerHandover", "Door sensor confirmed that the shipment was taken");
    }
}
