package parcelplus.lab10.waybill;

public final class Waybill {

    private final String sender;
    private final String receiverName;
    private final String receiverPhone;
    private final double weightKg;
    private final String dimensions;
    private final double declaredValue;
    private final double cashOnDelivery;
    private final boolean returnDocuments;
    private final String packaging;
    private final boolean insurance;

    Waybill(WaybillBuilder builder) {
        this.sender = builder.sender;
        this.receiverName = builder.receiverName;
        this.receiverPhone = builder.receiverPhone;
        this.weightKg = builder.weightKg;
        this.dimensions = builder.dimensions;
        this.declaredValue = builder.declaredValue;
        this.cashOnDelivery = builder.cashOnDelivery;
        this.returnDocuments = builder.returnDocuments;
        this.packaging = builder.packaging;
        this.insurance = builder.insurance;
    }

    @Override
    public String toString() {
        return "Waybill{sender=" + sender
                + ", receiver=" + receiverName + " (" + receiverPhone + ")"
                + ", weight=" + weightKg + " kg"
                + ", dimensions=" + (dimensions == null ? "none" : dimensions)
                + ", declaredValue=" + (declaredValue > 0 ? declaredValue : "none")
                + ", cashOnDelivery=" + (cashOnDelivery > 0 ? cashOnDelivery : "none")
                + ", returnDocuments=" + returnDocuments
                + ", packaging=" + (packaging == null ? "none" : packaging)
                + ", insurance=" + insurance + "}";
    }
}
