package parcelplus.lab12.flyweight;

public final class ShipmentType {

    private final String name;
    private final String icon;
    private final String color;
    private final String label;

    ShipmentType(String name, String icon, String color, String label) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String describe() {
        return name + "{icon=" + icon + ", color=" + color + ", label=" + label + "}";
    }
}
