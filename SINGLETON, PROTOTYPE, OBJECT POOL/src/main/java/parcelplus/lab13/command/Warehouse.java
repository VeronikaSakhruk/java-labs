package parcelplus.lab13.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import parcelplus.Log;

public class Warehouse {

    private final Map<String, String> locations = new HashMap<>();
    private final Set<String> damaged = new HashSet<>();

    public void addParcel(String parcelId, String location) {
        locations.put(parcelId, location);
        Log.log("Warehouse", "Parcel " + parcelId + " registered in " + location);
    }

    public String getLocation(String parcelId) {
        return locations.get(parcelId);
    }

    public boolean isDamaged(String parcelId) {
        return damaged.contains(parcelId);
    }

    public void place(String parcelId, String location) {
        String previous = locations.put(parcelId, location);
        Log.log("Warehouse", "Parcel " + parcelId + " moved from " + previous + " to " + location);
    }

    public void setDamaged(String parcelId, boolean value) {
        if (value) {
            damaged.add(parcelId);
        } else {
            damaged.remove(parcelId);
        }
        Log.log("Warehouse", "Parcel " + parcelId + (value ? " marked as damaged" : " damage mark removed"));
    }
}
