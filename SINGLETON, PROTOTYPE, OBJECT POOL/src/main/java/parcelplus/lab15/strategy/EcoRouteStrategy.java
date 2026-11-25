package parcelplus.lab15.strategy;

import java.util.ArrayList;
import java.util.List;

public class EcoRouteStrategy implements RouteStrategy {

    private static final double CONGESTED_FROM = 2.0;

    @Override
    public String name() {
        return "ecological route";
    }

    @Override
    public List<Address> build(Address depot, List<Address> addresses) {
        List<Address> congested = addresses.stream().filter(a -> a.congestion() >= CONGESTED_FROM).toList();
        List<Address> free = addresses.stream().filter(a -> a.congestion() < CONGESTED_FROM).toList();
        List<Address> route = new ArrayList<>(RouteStrategy.nearestFirst(depot, congested, Address::distanceTo));
        Address last = route.isEmpty() ? depot : route.get(route.size() - 1);
        route.addAll(RouteStrategy.nearestFirst(last, free, Address::distanceTo));
        return route;
    }
}
