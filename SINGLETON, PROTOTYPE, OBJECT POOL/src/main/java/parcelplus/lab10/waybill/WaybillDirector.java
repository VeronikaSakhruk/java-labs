package parcelplus.lab10.waybill;

public class WaybillDirector {

    public Waybill documents(String sender, String receiver, String phone) {
        return new WaybillBuilder()
                .sender(sender)
                .receiver(receiver)
                .receiverPhone(phone)
                .weight(0.3)
                .dimensions(35, 25, 2)
                .returnDocuments()
                .build();
    }

    public Waybill codParcel(String sender, String receiver, String phone, double weightKg) {
        return new WaybillBuilder()
                .sender(sender)
                .receiver(receiver)
                .receiverPhone(phone)
                .weight(weightKg)
                .dimensions(40, 30, 20)
                .cashOnDelivery(1500)
                .packaging("standard")
                .build();
    }
}
