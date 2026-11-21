package parcelplus.lab14.mediator;

import parcelplus.Log;

public class Scale {

    private final SortingMediator mediator;

    public Scale(SortingMediator mediator) {
        this.mediator = mediator;
    }

    public void weigh(String parcelId, double weightKg) {
        Log.log("Scale", "Measured " + parcelId + ": " + Log.fmt(weightKg) + " kg, reporting to coordinator");
        mediator.weightMeasured(parcelId, weightKg);
    }
}
