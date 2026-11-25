package parcelplus.lab15.template;

import parcelplus.Log;

public class BranchHandover extends HandoverProcedure {

    @Override
    protected void identifyRecipient(Shipment shipment) {
        Log.log("BranchHandover", "Passport of " + shipment.recipient() + " checked at the counter");
    }

    @Override
    protected void inspectContents(Shipment shipment) {
        Log.log("BranchHandover", "Recipient inspects the contents at the counter");
    }

    @Override
    protected void confirmReceipt(Shipment shipment) {
        Log.log("BranchHandover", "Signature received on the paper form");
    }
}
