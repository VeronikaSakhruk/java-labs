package parcelplus.lab15.template;

import parcelplus.Log;

public class CourierHandover extends HandoverProcedure {

    @Override
    protected void identifyRecipient(Shipment shipment) {
        Log.log("CourierHandover", "SMS code of " + shipment.recipient() + " verified by the courier");
    }

    @Override
    protected void confirmReceipt(Shipment shipment) {
        Log.log("CourierHandover", "Signature received on the courier tablet");
    }
}
