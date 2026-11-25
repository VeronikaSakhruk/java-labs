package parcelplus.lab15.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FewestLeftTurnsStrategy implements RouteStrategy {

    @Override
    public String name() {
        return "fewest left turns";
    }

    @Override
    public List<Address> build(Address depot, List<Address> addresses) {
        List<Address> route = new ArrayList<>(addresses);
        route.sort(Comparator.comparingDouble(address -> clockwiseAngle(depot, address)));
        return route;
    }

    private double clockwiseAngle(Address depot, Address address) {
        double angle = Math.PI / 2 - Math.atan2(address.y() - depot.y(), address.x() - depot.x());
        return angle < 0 ? angle + 2 * Math.PI : angle;
    }
}
