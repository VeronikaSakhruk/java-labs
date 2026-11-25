package parcelplus.lab15.strategy;

public record Address(String name, double x, double y, boolean urgent, double congestion) {

    public double distanceTo(Address other) {
        return Math.hypot(x - other.x, y - other.y);
    }
}
