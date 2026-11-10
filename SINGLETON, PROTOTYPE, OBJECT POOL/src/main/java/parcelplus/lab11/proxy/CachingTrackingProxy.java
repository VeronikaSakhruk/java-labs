package parcelplus.lab11.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongSupplier;

import parcelplus.Log;

public class CachingTrackingProxy implements TrackingService {
    private record CacheEntry(String status, long savedAt) {}

    private final TrackingService service;
    private final long ttlMinutes;
    private final LongSupplier clockMinutes;
    private final Map<String, CacheEntry> cache = new HashMap<>();

    public CachingTrackingProxy(TrackingService service, long ttlMinutes, LongSupplier clockMinutes) {
        this.service = service;
        this.ttlMinutes = ttlMinutes;
        this.clockMinutes = clockMinutes;
    }

    @Override
    public synchronized String getStatus(String shipmentId) {
        long now = clockMinutes.getAsLong();
        CacheEntry entry = cache.get(shipmentId);
        if (entry != null && now - entry.savedAt() < ttlMinutes) {
            Log.log("CachingTrackingProxy", "Status from cache for " + shipmentId + ": "
                    + entry.status() + " (age " + (now - entry.savedAt()) + " min)");
            return entry.status();
        }
        Log.log("CachingTrackingProxy", entry == null ? "No cached status for " + shipmentId
                : "Cached status for " + shipmentId + " expired");
        String status = service.getStatus(shipmentId);
        cache.put(shipmentId, new CacheEntry(status, now));
        return status;
    }
}
