package parcelplus.lab11.facade;

import parcelplus.Log;

public class ShipmentFacade {
    private final AddressValidator addressValidator = new AddressValidator();
    private final CostCalculator costCalculator = new CostCalculator();
    private final PaymentService paymentService = new PaymentService();
    private final WaybillGenerator waybillGenerator = new WaybillGenerator();
    private final RoutePlanner routePlanner = new RoutePlanner();
    private final RecipientNotifier recipientNotifier = new RecipientNotifier();

    public String register(String channel, String recipient, String address, double weightKg) {
        Log.log("ShipmentFacade", "Registration requested from " + channel + " for " + recipient);
        if (!addressValidator.validate(address)) {
            Log.log("ShipmentFacade", "Registration aborted: invalid address");
            return null;
        }
        double cost = costCalculator.calculate(weightKg);
        paymentService.charge(cost);
        String waybill = waybillGenerator.generate();
        routePlanner.plan(waybill, address);
        recipientNotifier.notifyRecipient(recipient, waybill);
        Log.log("ShipmentFacade", "Shipment registered from " + channel + ", waybill " + waybill);
        return waybill;
    }
}
