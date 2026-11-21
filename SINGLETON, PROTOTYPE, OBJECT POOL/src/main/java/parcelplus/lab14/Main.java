package parcelplus.lab14;

import parcelplus.Log;
import parcelplus.lab14.mediator.BarcodeScanner;
import parcelplus.lab14.mediator.IntakeBelt;
import parcelplus.lab14.mediator.LoadingZone;
import parcelplus.lab14.mediator.Scale;
import parcelplus.lab14.mediator.Sorter;
import parcelplus.lab14.mediator.SortingCenterController;
import parcelplus.lab14.observer.AccountingDepartment;
import parcelplus.lab14.observer.OnlineCabinet;
import parcelplus.lab14.observer.OnlineStore;
import parcelplus.lab14.observer.Receiver;
import parcelplus.lab14.observer.Sender;
import parcelplus.lab14.observer.Shipment;
import parcelplus.lab14.observer.ShipmentStatus;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipment status tracking (Observer) ===");
        demonstrateTracking();
        Log.log("Main", "=== Task 2: Sorting center (Mediator) ===");
        demonstrateSortingCenter();
    }

    private static void demonstrateTracking() {
        Shipment shipment = new Shipment("PS-7001");
        Sender sender = new Sender("Ivan Petrenko");
        Receiver receiver = new Receiver("Olena Shevchenko");
        shipment.subscribe(sender);
        shipment.subscribe(receiver);
        shipment.subscribe(new AccountingDepartment(1200));
        shipment.changeStatus(ShipmentStatus.IN_TRANSIT);

        shipment.subscribe(new OnlineCabinet());
        shipment.subscribe(new OnlineStore());
        shipment.changeStatus(ShipmentStatus.DELIVERED);

        Shipment second = new Shipment("PS-7002");
        Receiver secondReceiver = new Receiver("Taras Bondar");
        second.subscribe(new Sender("Ivan Petrenko"));
        second.subscribe(secondReceiver);
        second.unsubscribe(secondReceiver);
        second.changeStatus(ShipmentStatus.ARRIVED);
    }

    private static void demonstrateSortingCenter() {
        SortingCenterController controller = new SortingCenterController();
        IntakeBelt belt = new IntakeBelt();
        Sorter sorter = new Sorter();
        LoadingZone loadingZone = new LoadingZone(controller);
        controller.connect(belt, sorter, loadingZone);
        BarcodeScanner scanner = new BarcodeScanner(controller);
        Scale scale = new Scale(controller);

        scanner.scan("PS-8001", "Khotyn");
        scale.weigh("PS-8002", 58);
        loadingZone.requestLoading("BAG-17", "Khotyn");
        loadingZone.truckArrives("T-5", "Khotyn");
        loadingZone.requestLoading("BAG-17", "Khotyn");
    }
}
