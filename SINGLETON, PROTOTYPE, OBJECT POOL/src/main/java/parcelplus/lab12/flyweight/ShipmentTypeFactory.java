package parcelplus.lab12.flyweight;

import java.util.HashMap;
import java.util.Map;
import parcelplus.Log;

public class ShipmentTypeFactory {

    private static final Map<String, String[]> DEFINITIONS = Map.of(
            "STANDARD", new String[] {"box.png", "#757575", "Standard"},
            "FRAGILE", new String[] {"glass.png", "#E53935", "Fragile"},
            "COLD", new String[] {"snowflake.png", "#1E88E5", "Temperature sensitive"},
            "DOCUMENTS", new String[] {"envelope.png", "#43A047", "Documents"},
            "OVERSIZED", new String[] {"pallet.png", "#FB8C00", "Oversized"});

    private final Map<String, ShipmentType> types = new HashMap<>();

    public synchronized ShipmentType get(String name) {
        ShipmentType type = types.get(name);
        if (type == null) {
            String[] data = DEFINITIONS.get(name);
            if (data == null) {
                throw new IllegalArgumentException("Unknown shipment type: " + name);
            }
            type = new ShipmentType(name, data[0], data[1], data[2]);
            types.put(name, type);
            Log.log("ShipmentTypeFactory", "Shipment type object created: " + type.describe());
        }
        return type;
    }

    public synchronized int getCreatedCount() {
        return types.size();
    }
}
