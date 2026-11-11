package parcelplus.lab12.composite;

import java.util.ArrayList;
import java.util.List;
import parcelplus.Log;

public class CargoGroup implements CargoItem {

    private final String kind;
    private final String id;
    private final List<CargoItem> children = new ArrayList<>();

    public CargoGroup(String kind, String id) {
        this.kind = kind;
        this.id = id;
    }

    public CargoGroup add(CargoItem item) {
        children.add(item);
        return this;
    }

    @Override
    public String name() {
        return kind + " " + id;
    }

    @Override
    public double weightKg() {
        double sum = 0;
        for (CargoItem child : children) {
            sum += child.weightKg();
        }
        return sum;
    }

    @Override
    public double declaredValue() {
        double sum = 0;
        for (CargoItem child : children) {
            sum += child.declaredValue();
        }
        return sum;
    }

    @Override
    public int shipmentCount() {
        int sum = 0;
        for (CargoItem child : children) {
            sum += child.shipmentCount();
        }
        return sum;
    }

    @Override
    public void print(String indent) {
        Log.log("CargoTree", indent + describe());
        for (CargoItem child : children) {
            child.print(indent + "  ");
        }
    }
}
