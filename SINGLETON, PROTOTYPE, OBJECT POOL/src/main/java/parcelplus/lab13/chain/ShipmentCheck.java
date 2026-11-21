package parcelplus.lab13.chain;

import parcelplus.Log;

public abstract class ShipmentCheck {

    private ShipmentCheck next;

    public static ShipmentCheck chain(ShipmentCheck... checks) {
        if (checks.length == 0) {
            throw new IllegalArgumentException("A chain needs at least one check");
        }
        for (int i = 0; i < checks.length - 1; i++) {
            checks[i].next = checks[i + 1];
        }
        checks[checks.length - 1].next = null;
        return checks[0];
    }

    protected abstract String validate(Shipment shipment);

    public final boolean handle(Shipment shipment) {
        String name = getClass().getSimpleName();
        Log.log(name, "Checking shipment " + shipment.id());
        String reason = validate(shipment);
        if (reason != null) {
            Log.log(name, "Shipment " + shipment.id() + " rejected: " + reason);
            return false;
        }
        if (next == null) {
            Log.log(name, "Passed, end of chain");
            return true;
        }
        Log.log(name, "Passed, forwarding to " + next.getClass().getSimpleName());
        return next.handle(shipment);
    }
}
