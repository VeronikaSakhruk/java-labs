package parcelplus.lab15.strategy;

import java.util.ArrayList;
import java.util.List;

public class UrgentFirstStrategy implements RouteStrategy {

    @Override
    public String name() {
        return "urgent shipments first";
    }

    @Override
    public List<Address> build(Address depot, List<Address> addresses) {
        List<Address> urgent = addresses.stream().filter(Address::urgent).toList();
        List<Address> regular = addresses.stream().filter(a -> !a.urgent()).toList();
        List<Address> route = new ArrayList<>(RouteStrategy.nearestFirst(depot, urgent, Address::distanceTo));
        Address last = route.isEmpty() ? depot : route.get(route.size() - 1);
        route.addAll(RouteStrategy.nearestFirst(last, regular, Address::distanceTo));
        return route;
    }
}
