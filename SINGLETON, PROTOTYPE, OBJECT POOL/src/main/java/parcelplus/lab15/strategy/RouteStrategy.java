package parcelplus.lab15.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public interface RouteStrategy {

    String name();

    List<Address> build(Address depot, List<Address> addresses);

    static List<Address> nearestFirst(Address start, List<Address> pool, ToDoubleBiFunction<Address, Address> cost) {
        List<Address> left = new ArrayList<>(pool);
        List<Address> route = new ArrayList<>();
        Address current = start;
        while (!left.isEmpty()) {
            Address best = left.get(0);
            for (Address candidate : left) {
                if (cost.applyAsDouble(current, candidate) < cost.applyAsDouble(current, best)) {
                    best = candidate;
                }
            }
            left.remove(best);
            route.add(best);
            current = best;
        }
        return route;
    }
}
