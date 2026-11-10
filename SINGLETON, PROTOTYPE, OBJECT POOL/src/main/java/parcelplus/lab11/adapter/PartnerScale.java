package parcelplus.lab11.adapter;

import parcelplus.Log;

public class PartnerScale {
    public double getWeightPounds() {
        Log.log("PartnerScale", "Raw weight reading: 11.02 lb");
        return 11.0231;
    }

    public double[] getSizeInches() {
        Log.log("PartnerScale", "Raw size reading: 15.75 x 11.81 x 7.87 in");
        return new double[] {15.748, 11.811, 7.874};
    }
}
