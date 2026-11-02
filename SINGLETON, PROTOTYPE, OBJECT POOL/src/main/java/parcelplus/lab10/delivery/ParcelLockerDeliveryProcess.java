package parcelplus.lab10.delivery;

public class ParcelLockerDeliveryProcess extends DeliveryProcess {

    @Override
    protected Delivery createDelivery() {
        return new ParcelLockerDelivery();
    }

    private record ParcelLockerDelivery() implements Delivery {
        public String name() {
            return "Parcel locker delivery";
        }

        public double cost(double weightKg) {
            return 45 + 3 * weightKg;
        }

        public int days() {
            return 2;
        }

        public String executor() {
            return "parcel locker L-12";
        }
    }
}
