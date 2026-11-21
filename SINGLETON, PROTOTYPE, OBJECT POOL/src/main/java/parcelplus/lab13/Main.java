package parcelplus.lab13;

import parcelplus.Log;
import parcelplus.lab13.chain.CustomsDeclarationCheck;
import parcelplus.lab13.chain.DimensionsCheck;
import parcelplus.lab13.chain.LithiumBatteryCheck;
import parcelplus.lab13.chain.ProhibitedItemsCheck;
import parcelplus.lab13.chain.Shipment;
import parcelplus.lab13.chain.ShipmentCheck;
import parcelplus.lab13.chain.WaybillCompletenessCheck;
import parcelplus.lab13.chain.WeightLimitCheck;
import parcelplus.lab13.command.LoadIntoVehicleCommand;
import parcelplus.lab13.command.MarkDamagedCommand;
import parcelplus.lab13.command.MoveToSectorCommand;
import parcelplus.lab13.command.OperationQueue;
import parcelplus.lab13.command.Warehouse;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipment acceptance checks (Chain of Responsibility) ===");
        demonstrateAcceptance();
        Log.log("Main", "=== Task 2: Sorting warehouse operations (Command) ===");
        demonstrateWarehouse();
    }

    private static void demonstrateAcceptance() {
        ShipmentCheck domestic = ShipmentCheck.chain(
                new WaybillCompletenessCheck(),
                new ProhibitedItemsCheck(),
                new WeightLimitCheck(),
                new DimensionsCheck());
        ShipmentCheck international = ShipmentCheck.chain(
                new WaybillCompletenessCheck(),
                new ProhibitedItemsCheck(),
                new WeightLimitCheck(),
                new DimensionsCheck(),
                new CustomsDeclarationCheck());

        submit(international, shipment("PS-6001", 12.5, true, false, "CUSTOMS-0045"));
        submit(domestic, shipment("PS-6002", 8.0, false, false, null));
        submit(international, shipment("PS-6003", 45, true, false, "CUSTOMS-0046"));

        Log.log("Main", "Lithium battery check added to the international chain");
        ShipmentCheck extended = ShipmentCheck.chain(
                new WaybillCompletenessCheck(),
                new ProhibitedItemsCheck(),
                new LithiumBatteryCheck(),
                new WeightLimitCheck(),
                new DimensionsCheck(),
                new CustomsDeclarationCheck());
        submit(extended, shipment("PS-6004", 2.0, true, true, "CUSTOMS-0047"));
    }

    private static Shipment shipment(String id, double weightKg, boolean international, boolean lithium,
                                     String customs) {
        return new Shipment(id, "Ivan Petrenko", "Olena Shevchenko", "books and clothes", weightKg,
                50, 40, 30, international, customs, lithium);
    }

    private static void submit(ShipmentCheck chain, Shipment shipment) {
        Log.log("Main", "Submitting " + shipment.id() + " ("
                + (shipment.international() ? "international" : "domestic") + ", "
                + Log.fmt(shipment.weightKg()) + " kg) to the chain");
        boolean accepted = chain.handle(shipment);
        Log.log("Main", "Result for " + shipment.id() + ": " + (accepted ? "ACCEPTED" : "REJECTED"));
    }

    private static void demonstrateWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.addParcel("PS-2001", "sector A1");
        warehouse.addParcel("PS-2002", "sector A2");
        warehouse.addParcel("PS-2003", "sector A3");
        warehouse.addParcel("PS-2004", "sector B1");
        warehouse.addParcel("PS-2005", "sector A1");

        OperationQueue queue = new OperationQueue();
        Log.log("Main", "Scanner sends 5 operations in a row");
        queue.submit(new MoveToSectorCommand(warehouse, "PS-2001", "sector B2"));
        queue.submit(new LoadIntoVehicleCommand(warehouse, "PS-2002", "V-12"));
        queue.submit(new MarkDamagedCommand(warehouse, "PS-2003"));
        queue.submit(new MoveToSectorCommand(warehouse, "PS-2004", "sector C1"));
        queue.submit(new LoadIntoVehicleCommand(warehouse, "PS-2005", "V-12"));

        queue.processAll();

        Log.log("Main", "Operator reports a mistaken scan: the last loading must be cancelled");
        queue.undoLast();
        Log.log("Main", "PS-2005 is now in " + warehouse.getLocation("PS-2005"));

        queue.printJournal();
    }
}
