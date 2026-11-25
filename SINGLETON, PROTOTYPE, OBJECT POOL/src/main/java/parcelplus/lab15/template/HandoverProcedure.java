package parcelplus.lab15.template;

import parcelplus.Log;

public abstract class HandoverProcedure {

    public final void handOver(Shipment shipment) {
        String source = getClass().getSimpleName();
        Log.log(source, "Handover of " + shipment.id() + " started");
        step(1, "identify recipient");
        identifyRecipient(shipment);
        step(2, "check payment");
        checkPayment(shipment);
        step(3, "inspect contents");
        inspectContents(shipment);
        step(4, "issue shipment");
        issueShipment(shipment);
        step(5, "receive confirmation");
        confirmReceipt(shipment);
        step(6, "close waybill");
        closeWaybill(shipment);
        Log.log(source, "Handover of " + shipment.id() + " finished");
    }

    protected abstract void identifyRecipient(Shipment shipment);

    protected abstract void confirmReceipt(Shipment shipment);

    protected void inspectContents(Shipment shipment) {
        Log.log(getClass().getSimpleName(), "Contents inspection not requested (default behavior)");
    }

    private void step(int number, String title) {
        Log.log(getClass().getSimpleName(), "Step " + number + "/6: " + title);
    }

    private void checkPayment(Shipment shipment) {
        if (shipment.cashOnDelivery() > 0) {
            Log.log(getClass().getSimpleName(), "Cash on delivery " + Log.fmt(shipment.cashOnDelivery())
                    + " UAH verified and collected");
        } else {
            Log.log(getClass().getSimpleName(), "No cash on delivery, nothing to collect");
        }
    }

    private void issueShipment(Shipment shipment) {
        Log.log(getClass().getSimpleName(), "Shipment " + shipment.id() + " issued to " + shipment.recipient());
    }

    private void closeWaybill(Shipment shipment) {
        Log.log(getClass().getSimpleName(), "Waybill of " + shipment.id() + " closed");
    }
}
