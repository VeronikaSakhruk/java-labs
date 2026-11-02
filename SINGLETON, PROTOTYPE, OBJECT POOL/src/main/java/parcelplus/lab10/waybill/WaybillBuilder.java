package parcelplus.lab10.waybill;

import parcelplus.Log;

public class WaybillBuilder {

    String sender;
    String receiverName;
    String receiverPhone;
    double weightKg;
    String dimensions;
    double declaredValue;
    double cashOnDelivery;
    boolean returnDocuments;
    String packaging;
    boolean insurance;

    public WaybillBuilder sender(String sender) {
        this.sender = sender;
        Log.log("WaybillBuilder", "Sender set: " + sender);
        return this;
    }

    public WaybillBuilder receiver(String name) {
        this.receiverName = name;
        Log.log("WaybillBuilder", "Receiver set: " + name);
        return this;
    }

    public WaybillBuilder receiverPhone(String phone) {
        this.receiverPhone = phone;
        Log.log("WaybillBuilder", "Receiver phone set: " + phone);
        return this;
    }

    public WaybillBuilder weight(double weightKg) {
        this.weightKg = weightKg;
        Log.log("WaybillBuilder", "Weight set: " + weightKg + " kg");
        return this;
    }

    public WaybillBuilder dimensions(int length, int width, int height) {
        this.dimensions = length + "x" + width + "x" + height + " cm";
        Log.log("WaybillBuilder", "Dimensions set: " + dimensions);
        return this;
    }

    public WaybillBuilder declaredValue(double amount) {
        this.declaredValue = amount;
        Log.log("WaybillBuilder", "Declared value set: " + amount);
        return this;
    }

    public WaybillBuilder cashOnDelivery(double amount) {
        this.cashOnDelivery = amount;
        Log.log("WaybillBuilder", "Cash on delivery set: " + amount);
        return this;
    }

    public WaybillBuilder returnDocuments() {
        this.returnDocuments = true;
        Log.log("WaybillBuilder", "Return of documents added");
        return this;
    }

    public WaybillBuilder packaging(String type) {
        this.packaging = type;
        Log.log("WaybillBuilder", "Packaging added: " + type);
        return this;
    }

    public WaybillBuilder insurance() {
        this.insurance = true;
        Log.log("WaybillBuilder", "Insurance added");
        return this;
    }

    public Waybill build() {
        String error = validate();
        if (error != null) {
            String message = "Cannot create waybill: " + error;
            Log.log("WaybillBuilder", message);
            throw new IllegalStateException(message);
        }
        Waybill waybill = new Waybill(this);
        Log.log("WaybillBuilder", "Waybill created");
        return waybill;
    }

    private String validate() {
        if (sender == null || sender.isBlank()) {
            return "sender is not specified";
        }
        if (receiverName == null || receiverName.isBlank()) {
            return "receiver is not specified";
        }
        if (receiverPhone == null || receiverPhone.isBlank()) {
            return "receiver phone is not specified";
        }
        if (!(weightKg > 0)) {
            return "weight must be greater than zero";
        }
        return null;
    }
}
