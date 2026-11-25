package parcelplus.lab15.strategy;

import java.util.List;
import java.util.stream.Collectors;
import parcelplus.Log;

public class CourierRoutePlanner {

    private final String courier;
    private RouteStrategy strategy;

    public CourierRoutePlanner(String courier, RouteStrategy strategy) {
        this.courier = courier;
        this.strategy = strategy;
    }

    public synchronized void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
        Log.log("CourierRoutePlanner", "Strategy for " + courier + " changed to '" + strategy.name() + "'");
    }

    public synchronized List<Address> plan(Address depot, List<Address> addresses) {
        Log.log("CourierRoutePlanner", "Building route for " + courier + " with strategy '"
                + strategy.name() + "' for " + addresses.size() + " addresses");
        List<Address> route = strategy.build(depot, addresses);
        Log.log(strategy.getClass().getSimpleName(), "Route: " + route.stream()
                .map(Address::name).collect(Collectors.joining(" -> ")));
        Log.log("CourierRoutePlanner", "Route length " + Log.fmt(length(depot, route)) + " km");
        return route;
    }

    private double length(Address depot, List<Address> route) {
        double total = 0;
        Address current = depot;
        for (Address address : route) {
            total += current.distanceTo(address);
            current = address;
        }
        return total;
    }
}
