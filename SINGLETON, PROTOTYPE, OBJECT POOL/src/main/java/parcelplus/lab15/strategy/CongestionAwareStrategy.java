package parcelplus.lab15.strategy;

import java.util.List;

public class CongestionAwareStrategy implements RouteStrategy {

    @Override
    public String name() {
        return "congestion aware";
    }

    @Override
    public List<Address> build(Address depot, List<Address> addresses) {
        return RouteStrategy.nearestFirst(depot, addresses,
                (from, to) -> from.distanceTo(to) * to.congestion());
    }
}
