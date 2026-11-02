package parcelplus.lab10.delivery;

import java.util.Locale;
import parcelplus.Log;

public abstract class DeliveryProcess {

    protected abstract Delivery createDelivery();

    public final void arrange(String shipmentId, String address, double weightKg) {
        Log.log("DeliveryProcess", "Arranging delivery of " + shipmentId + " to " + address);
        if (address == null || address.isBlank()) {
            Log.log("DeliveryProcess", "Address is not valid, delivery of " + shipmentId + " not arranged");
            return;
        }
        Log.log("DeliveryProcess", "Address verified");
        Delivery delivery = createDelivery();
        Log.log("DeliveryProcess", delivery.name() + ": cost "
                + String.format(Locale.ROOT, "%.2f", delivery.cost(weightKg))
                + " UAH, term " + delivery.days() + " day(s)");
        Log.log("DeliveryProcess", "Executor assigned: " + delivery.executor());
        Log.log("DeliveryProcess", "Delivery of " + shipmentId + " registered via " + delivery.name());
    }
}
