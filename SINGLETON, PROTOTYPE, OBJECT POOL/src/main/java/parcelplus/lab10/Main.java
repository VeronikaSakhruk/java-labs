package parcelplus.lab10;

import java.util.List;
import parcelplus.Log;
import parcelplus.lab10.delivery.BranchPickupDeliveryProcess;
import parcelplus.lab10.delivery.CourierDeliveryProcess;
import parcelplus.lab10.delivery.DeliveryProcess;
import parcelplus.lab10.delivery.FloorDeliveryProcess;
import parcelplus.lab10.delivery.ParcelLockerDeliveryProcess;
import parcelplus.lab10.packaging.ColdKitFactory;
import parcelplus.lab10.packaging.FragileKitFactory;
import parcelplus.lab10.packaging.OversizedKitFactory;
import parcelplus.lab10.packaging.PackingStation;
import parcelplus.lab10.packaging.StandardKitFactory;
import parcelplus.lab10.waybill.Waybill;
import parcelplus.lab10.waybill.WaybillBuilder;
import parcelplus.lab10.waybill.WaybillDirector;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipment packaging (Abstract Factory) ===");
        demonstratePackaging();
        Log.log("Main", "=== Task 2: Delivery methods (Factory Method) ===");
        demonstrateDelivery();
        Log.log("Main", "=== Task 3: Waybill creation (Builder) ===");
        demonstrateWaybills();
    }

    private static void demonstratePackaging() {
        PackingStation station = new PackingStation();
        station.pack("PS-2001", new StandardKitFactory());
        station.pack("PS-2002", new FragileKitFactory());
        station.pack("PS-2003", new ColdKitFactory());
        Log.log("Main", "Oversized type added with new classes, PackingStation unchanged");
        station.pack("PS-2004", new OversizedKitFactory());
    }

    private static void demonstrateDelivery() {
        List<DeliveryProcess> processes = List.of(
                new CourierDeliveryProcess(),
                new ParcelLockerDeliveryProcess(),
                new BranchPickupDeliveryProcess());
        for (DeliveryProcess process : processes) {
            process.arrange("PS-3001", "Chernivtsi, Holovna St 10", 2.5);
        }
        Log.log("Main", "Floor delivery added with new classes, DeliveryProcess unchanged");
        new FloorDeliveryProcess().arrange("PS-3001", "Chernivtsi, Holovna St 10", 2.5);
    }

    private static void demonstrateWaybills() {
        Waybill full = new WaybillBuilder()
                .sender("Ivan Petrenko")
                .receiver("Olena Shevchenko")
                .receiverPhone("+380501234567")
                .weight(2.5)
                .dimensions(40, 30, 20)
                .declaredValue(5000)
                .cashOnDelivery(1200)
                .insurance()
                .build();
        Log.log("Main", "Full waybill: " + full);

        WaybillDirector director = new WaybillDirector();
        Waybill documents = director.documents("Ivan Petrenko", "Olena Shevchenko", "+380501234567");
        Log.log("Main", "Typical 'documents' waybill: " + documents);
        Waybill parcel = director.codParcel("Ivan Petrenko", "Taras Bondar", "+380671112233", 3.2);
        Log.log("Main", "Typical 'cash on delivery parcel' waybill: " + parcel);

        try {
            new WaybillBuilder()
                    .sender("Ivan Petrenko")
                    .receiver("Olena Shevchenko")
                    .weight(1.0)
                    .build();
        } catch (IllegalStateException e) {
            Log.log("Main", "Waybill was not created");
        }
    }
}
