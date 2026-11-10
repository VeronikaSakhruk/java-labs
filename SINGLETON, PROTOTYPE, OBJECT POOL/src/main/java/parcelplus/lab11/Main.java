package parcelplus.lab11;

import parcelplus.Log;
import parcelplus.lab11.adapter.LegacyScale;
import parcelplus.lab11.adapter.PartnerScale;
import parcelplus.lab11.adapter.PartnerScaleAdapter;
import parcelplus.lab11.adapter.ShipmentIntake;
import parcelplus.lab11.decorator.BasicShipment;
import parcelplus.lab11.decorator.CashOnDeliveryDecorator;
import parcelplus.lab11.decorator.InsuranceDecorator;
import parcelplus.lab11.decorator.PackagingDecorator;
import parcelplus.lab11.decorator.Shipment;
import parcelplus.lab11.decorator.SmsNotificationDecorator;
import parcelplus.lab11.decorator.WeekendDeliveryDecorator;
import parcelplus.lab11.facade.CostCalculator;
import parcelplus.lab11.facade.ShipmentFacade;
import parcelplus.lab11.proxy.CachingTrackingProxy;
import parcelplus.lab11.proxy.PartnerTrackingService;
import parcelplus.lab11.proxy.TrackingService;

public class Main {

    public static void main(String[] args) {
        Log.log("Main", "=== Task 1: Shipment registration (Facade) ===");
        demonstrateRegistration();
        Log.log("Main", "=== Task 2: Additional services (Decorator) ===");
        demonstrateServices();
        Log.log("Main", "=== Task 3: Partner tracking (Proxy) ===");
        demonstrateTracking();
        Log.log("Main", "=== Task 4: Partner scales (Adapter) ===");
        demonstrateScales();
    }

    private static void demonstrateRegistration() {
        ShipmentFacade facade = new ShipmentFacade();
        facade.register("Branch #1", "Olena Shevchenko", "Chernivtsi, Holovna St 10", 2.5);
        facade.register("Online cabinet", "Taras Bondar", "Khotyn, Shevchenka St 5", 1.2);
        Log.log("Main", "Cost calculation used separately, without registration");
        new CostCalculator().calculate(4.0);
    }

    private static void demonstrateServices() {
        Shipment plain = new BasicShipment("PS-4001", 2.5);
        Log.log("Main", plain.description());
        Log.log("Main", "Cost: " + Log.fmt(plain.cost()) + " UAH");

        Shipment extended = new CashOnDeliveryDecorator(
                new PackagingDecorator(
                        new InsuranceDecorator(plain)));
        Log.log("Main", extended.description());
        Log.log("Main", "Cost: " + Log.fmt(extended.cost()) + " UAH");

        Shipment second = new SmsNotificationDecorator(
                new SmsNotificationDecorator(
                        new WeekendDeliveryDecorator(
                                new BasicShipment("PS-4002", 1.0))));
        Log.log("Main", second.description());
        Log.log("Main", "Cost: " + Log.fmt(second.cost()) + " UAH");
    }

    private static void demonstrateTracking() {
        long[] minutes = {0};
        PartnerTrackingService partner = new PartnerTrackingService();
        TrackingService tracking = new CachingTrackingProxy(partner, 5, () -> minutes[0]);

        Log.log("Main", "Status: " + tracking.getStatus("PS-5001"));
        for (int i = 1; i <= 3; i++) {
            minutes[0] += 1;
            Log.log("Main", "Status: " + tracking.getStatus("PS-5001"));
        }
        minutes[0] += 10;
        Log.log("Main", "Time advanced, now " + minutes[0] + " min");
        Log.log("Main", "Status: " + tracking.getStatus("PS-5001"));
        Log.log("Main", "Paid requests to partner: " + partner.getPaidRequests());
    }

    private static void demonstrateScales() {
        ShipmentIntake intake = new ShipmentIntake();
        intake.quote("Branch #1 (old scale)", new LegacyScale());
        intake.quote("Branch #2 (partner scale)", new PartnerScaleAdapter(new PartnerScale()));
    }
}
