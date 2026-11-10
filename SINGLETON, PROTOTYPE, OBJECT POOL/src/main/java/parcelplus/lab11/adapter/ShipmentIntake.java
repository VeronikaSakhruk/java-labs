package parcelplus.lab11.adapter;

import parcelplus.Log;

public class ShipmentIntake {
    public double quote(String branch, Scale scale) {
        double weightKg = scale.weightKg();
        double[] size = scale.sizeCm();
        double cost = 30 + 10 * weightKg;
        Log.log("ShipmentIntake", branch + ": " + Log.fmt(weightKg) + " kg, "
                + Log.fmt(size[0]) + " x " + Log.fmt(size[1]) + " x " + Log.fmt(size[2])
                + " cm, cost " + Log.fmt(cost) + " UAH");
        return cost;
    }
}
