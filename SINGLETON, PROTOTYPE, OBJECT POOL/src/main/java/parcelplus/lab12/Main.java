package parcelplus.lab12;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import parcelplus.Log;
import parcelplus.lab12.bridge.ArrivalNotification;
import parcelplus.lab12.bridge.DelayNotification;
import parcelplus.lab12.bridge.EmailChannel;
import parcelplus.lab12.bridge.PaymentNotification;
import parcelplus.lab12.bridge.SmsChannel;
import parcelplus.lab12.bridge.TelegramChannel;
import parcelplus.lab12.bridge.ViberChannel;
import parcelplus.lab12.composite.CargoGroup;
import parcelplus.lab12.composite.CargoItem;
import parcelplus.lab12.composite.Parcel;
import parcelplus.lab12.flyweight.ShipmentMarker;
import parcelplus.lab12.flyweight.ShipmentTypeFactory;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipments on the map (Flyweight) ===");
        demonstrateMap();
        Log.log("Main", "=== Task 2: Client notifications (Bridge) ===");
        demonstrateNotifications();
        Log.log("Main", "=== Task 3: Cargo consolidation (Composite) ===");
        demonstrateConsolidation();
    }

    private static void demonstrateMap() {
        ShipmentTypeFactory factory = new ShipmentTypeFactory();
        String[] typeNames = {"STANDARD", "FRAGILE", "COLD", "DOCUMENTS", "OVERSIZED"};
        String[] statuses = {"In transit", "At sorting center", "Out for delivery"};
        Random random = new Random(42);
        List<ShipmentMarker> markers = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            markers.add(new ShipmentMarker(
                    "PS-" + (100000 + i),
                    46 + random.nextDouble() * 5,
                    22 + random.nextDouble() * 13,
                    statuses[random.nextInt(statuses.length)],
                    factory.get(typeNames[random.nextInt(typeNames.length)])));
        }

        Log.log("Main", "Markers created: " + markers.size());
        Log.log("Main", "Shipment type objects created: " + factory.getCreatedCount());

        ShipmentMarker first = null;
        ShipmentMarker second = null;
        for (ShipmentMarker marker : markers) {
            if (marker.getType().getName().equals("FRAGILE")) {
                if (first == null) {
                    first = marker;
                } else {
                    second = marker;
                    break;
                }
            }
        }
        Log.log("Main", "First marker : " + first.describe());
        Log.log("Main", "Second marker: " + second.describe());
        Log.log("Main", "Same type object: " + (first.getType() == second.getType())
                + " (identity " + System.identityHashCode(first.getType()) + " and "
                + System.identityHashCode(second.getType()) + ")");
    }

    private static void demonstrateNotifications() {
        new ArrivalNotification(new SmsChannel(), "Branch #1").send("Olena Shevchenko");
        new ArrivalNotification(new EmailChannel(), "Branch #1").send("Olena Shevchenko");
        new DelayNotification(new ViberChannel(), 40).send("Taras Bondar");
        Log.log("Main", "Telegram channel added with one new class");
        new PaymentNotification(new TelegramChannel(), 150).send("Taras Bondar");
    }

    private static void demonstrateConsolidation() {
        CargoGroup nestedBag = new CargoGroup("Bag", "B-2")
                .add(new Parcel("PS-1003", 0.5, 150))
                .add(new Parcel("PS-1004", 3.0, 2000));
        CargoGroup bag1 = new CargoGroup("Bag", "B-1")
                .add(new Parcel("PS-1001", 2.5, 1200))
                .add(new Parcel("PS-1002", 1.0, 300))
                .add(nestedBag);
        CargoGroup bag3 = new CargoGroup("Bag", "B-3")
                .add(new Parcel("PS-1005", 4.2, 800));
        CargoGroup bag4 = new CargoGroup("Bag", "B-4")
                .add(new Parcel("PS-1006", 1.8, 950))
                .add(new Parcel("PS-1007", 0.9, 100));

        CargoGroup pallet1 = new CargoGroup("Pallet", "P-1").add(bag1).add(bag3);
        CargoGroup pallet2 = new CargoGroup("Pallet", "P-2").add(bag4).add(new Parcel("PS-1008", 12.0, 5000));
        CargoGroup trip = new CargoGroup("Trip", "T-77").add(pallet1).add(pallet2);

        trip.print("");
        report(trip);
        report(pallet1);
    }

    private static void report(CargoItem item) {
        Log.log("Main", "Totals for " + item.name() + ": weight " + Log.fmt(item.weightKg())
                + " kg, declared value " + Log.fmt(item.declaredValue())
                + " UAH, shipments " + item.shipmentCount());
    }
}
