package parcelplus.lab11.facade;

import parcelplus.Log;

public class CostCalculator {
    public double calculate(double weightKg) {
        double cost = 50 + 12 * weightKg;
        Log.log("CostCalculator", "Cost for " + weightKg + " kg: " + Log.fmt(cost) + " UAH");
        return cost;
    }
}
