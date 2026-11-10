package parcelplus.lab11.adapter;

import parcelplus.Log;

public class LegacyScale implements Scale {
    @Override
    public double weightKg() {
        Log.log("LegacyScale", "Weight reading: 3.20 kg");
        return 3.2;
    }

    @Override
    public double[] sizeCm() {
        Log.log("LegacyScale", "Size reading: 30.00 x 20.00 x 15.00 cm");
        return new double[] {30, 20, 15};
    }
}
