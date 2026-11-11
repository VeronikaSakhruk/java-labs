package parcelplus.lab12.composite;

import parcelplus.Log;

public class Parcel implements CargoItem {

    private final String waybill;
    private final double weightKg;
    private final double declaredValue;

    public Parcel(String waybill, double weightKg, double declaredValue) {
        this.waybill = waybill;
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    @Override
    public String name() {
        return "Parcel " + waybill;
    }

    @Override
    public double weightKg() {
        return weightKg;
    }

    @Override
    public double declaredValue() {
        return declaredValue;
    }

    @Override
    public int shipmentCount() {
        return 1;
    }

    @Override
    public void print(String indent) {
        Log.log("CargoTree", indent + describe());
    }
}
