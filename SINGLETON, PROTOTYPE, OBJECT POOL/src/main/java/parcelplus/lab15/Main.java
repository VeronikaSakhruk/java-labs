package parcelplus.lab15;

import java.util.List;
import parcelplus.Log;
import parcelplus.lab15.state.Parcel;
import parcelplus.lab15.strategy.Address;
import parcelplus.lab15.strategy.CongestionAwareStrategy;
import parcelplus.lab15.strategy.CourierRoutePlanner;
import parcelplus.lab15.strategy.EcoRouteStrategy;
import parcelplus.lab15.strategy.FewestLeftTurnsStrategy;
import parcelplus.lab15.strategy.ShortestDistanceStrategy;
import parcelplus.lab15.strategy.UrgentFirstStrategy;
import parcelplus.lab15.template.BranchHandover;
import parcelplus.lab15.template.CourierHandover;
import parcelplus.lab15.template.ParcelLockerHandover;
import parcelplus.lab15.template.Shipment;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipment handover (Template Method) ===");
        demonstrateHandover();
        Log.log("Main", "=== Task 2: Shipment state (State) ===");
        demonstrateStates();
        Log.log("Main", "=== Task 3: Courier route building (Strategy) ===");
        demonstrateRoutes();
    }

    private static void demonstrateHandover() {
        new BranchHandover().handOver(new Shipment("PS-9001", "Olena Shevchenko", 0));
        new ParcelLockerHandover().handOver(new Shipment("PS-9002", "Taras Bondar", 0));
        new CourierHandover().handOver(new Shipment("PS-9003", "Iryna Melnyk", 850));
    }

    private static void demonstrateStates() {
        Parcel delivered = new Parcel("PS-9101", "Chernivtsi, Holovna St 10");
        delivered.advance();
        delivered.advance();
        delivered.advance();
        delivered.handOver();

        Parcel moving = new Parcel("PS-9102", "Chernivtsi, Ruska St 5");
        moving.changeAddress("Chernivtsi, Kobylianska St 3");
        moving.advance();
        moving.advance();
        moving.changeAddress("Chernivtsi, Zelena St 12");
        moving.redirect("Khotyn, Shevchenka St 7");

        Parcel stored = new Parcel("PS-9103", "Khotyn, Shevchenka St 7");
        stored.advance();
        stored.advance();
        stored.advance();
        stored.storageDaysPassed(3);
        stored.storageDaysPassed(5);
    }

    private static void demonstrateRoutes() {
        Address depot = new Address("Depot", 0, 0, false, 1.0);
        List<Address> addresses = List.of(
                new Address("Holovna 10", 2, 1, false, 1.0),
                new Address("Ruska 5", 5, 6, true, 2.5),
                new Address("Kobylianska 3", -3, 4, false, 1.2),
                new Address("Shevchenka 7", 4, -2, true, 1.0),
                new Address("Universytetska 1", -4, -3, false, 3.0),
                new Address("Zelena 12", 1, 7, false, 1.5));

        CourierRoutePlanner planner = new CourierRoutePlanner("Ivanenko", new ShortestDistanceStrategy());
        Log.log("Main", "Monday");
        planner.plan(depot, addresses);

        Log.log("Main", "Tuesday");
        planner.setStrategy(new UrgentFirstStrategy());
        planner.plan(depot, addresses);

        Log.log("Main", "Wednesday");
        planner.setStrategy(new FewestLeftTurnsStrategy());
        planner.plan(depot, addresses);

        Log.log("Main", "Friday");
        planner.setStrategy(new CongestionAwareStrategy());
        planner.plan(depot, addresses);

        Log.log("Main", "Ecological strategy added with one new class");
        planner.setStrategy(new EcoRouteStrategy());
        planner.plan(depot, addresses);
    }
}
