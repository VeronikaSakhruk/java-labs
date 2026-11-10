package parcelplus.lab11.facade;

import parcelplus.Log;

public class PaymentService {
    public void charge(double amount) {
        Log.log("PaymentService", "Payment of " + Log.fmt(amount) + " UAH accepted");
    }
}
