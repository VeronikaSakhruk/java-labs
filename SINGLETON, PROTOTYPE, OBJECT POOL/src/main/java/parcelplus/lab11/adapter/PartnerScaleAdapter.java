package parcelplus.lab11.adapter;

import parcelplus.Log;

public class PartnerScaleAdapter implements Scale {
    private static final double KG_PER_POUND = 0.45359237;
    private static final double CM_PER_INCH = 2.54;
    private final PartnerScale partnerScale;

    public PartnerScaleAdapter(PartnerScale partnerScale) {
        this.partnerScale = partnerScale;
    }

    @Override
    public double weightKg() {
        double pounds = partnerScale.getWeightPounds();
        double kg = pounds * KG_PER_POUND;
        Log.log("PartnerScaleAdapter", "Converted " + Log.fmt(pounds) + " lb to " + Log.fmt(kg) + " kg");
        return kg;
    }

    @Override
    public double[] sizeCm() {
        double[] inches = partnerScale.getSizeInches();
        double[] cm = new double[inches.length];
        for (int i = 0; i < inches.length; i++) {
            cm[i] = inches[i] * CM_PER_INCH;
        }
        Log.log("PartnerScaleAdapter", "Converted size to " + Log.fmt(cm[0]) + " x "
                + Log.fmt(cm[1]) + " x " + Log.fmt(cm[2]) + " cm");
        return cm;
    }
}
