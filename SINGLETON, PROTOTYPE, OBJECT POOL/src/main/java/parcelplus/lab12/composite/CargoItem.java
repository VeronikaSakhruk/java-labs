package parcelplus.lab12.composite;

import parcelplus.Log;

public interface CargoItem {

    String name();

    double weightKg();

    double declaredValue();

    int shipmentCount();

    void print(String indent);

    default String describe() {
        return name() + " [weight=" + Log.fmt(weightKg()) + " kg, value=" + Log.fmt(declaredValue())
                + " UAH, shipments=" + shipmentCount() + "]";
    }
}
