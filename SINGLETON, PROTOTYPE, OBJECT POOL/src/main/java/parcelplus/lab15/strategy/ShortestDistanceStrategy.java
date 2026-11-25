package parcelplus.lab15.strategy;

import java.util.List;

public class ShortestDistanceStrategy implements RouteStrategy {

    @Override
    public String name() {
        return "shortest distance";
    }

    @Override
    public List<Address> build(Address depot, List<Address> addresses) {
        return RouteStrategy.nearestFirst(depot, addresses, Address::distanceTo);
    }
}
