package parcelplus.lab14.observer;

import parcelplus.Log;

public class AccountingDepartment implements StatusObserver {

    private final double cashOnDelivery;

    public AccountingDepartment(double cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    @Override
    public void onStatusChanged(String shipmentId, ShipmentStatus status) {
        if (status == ShipmentStatus.DELIVERED) {
            Log.log("AccountingDepartment", "Cash on delivery payment " + Log.fmt(cashOnDelivery)
                    + " UAH recorded for " + shipmentId);
        } else {
            Log.log("AccountingDepartment", "Status " + status + " of " + shipmentId + " ignored");
        }
    }
}
