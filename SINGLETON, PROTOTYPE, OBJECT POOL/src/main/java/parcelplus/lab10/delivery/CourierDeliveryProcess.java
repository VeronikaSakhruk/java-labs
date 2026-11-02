package parcelplus.lab10.delivery;

public class CourierDeliveryProcess extends DeliveryProcess {

    @Override
    protected Delivery createDelivery() {
        return new CourierDelivery();
    }

    private record CourierDelivery() implements Delivery {
        public String name() {
            return "Courier delivery";
        }

        public double cost(double weightKg) {
            return 80 + 6 * weightKg;
        }

        public int days() {
            return 1;
        }

        public String executor() {
            return "courier Ivanenko";
        }
    }
}
