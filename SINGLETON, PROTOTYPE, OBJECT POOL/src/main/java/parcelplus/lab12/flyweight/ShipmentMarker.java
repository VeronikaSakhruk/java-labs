package parcelplus.lab12.flyweight;

import parcelplus.Log;

public class ShipmentMarker {

    private final String waybill;
    private final double latitude;
    private final double longitude;
    private final String status;
    private final ShipmentType type;

    public ShipmentMarker(String waybill, double latitude, double longitude, String status, ShipmentType type) {
        this.waybill = waybill;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.type = type;
    }

    public ShipmentType getType() {
        return type;
    }

    public String describe() {
        return "Marker{waybill=" + waybill + ", position=" + Log.fmt(latitude) + "," + Log.fmt(longitude)
                + ", status=" + status + ", type=" + type.describe() + "}";
    }
}
