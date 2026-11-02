package parcelplus.lab10.delivery;

public class FloorDeliveryProcess extends DeliveryProcess {

    @Override
    protected Delivery createDelivery() {
        return new FloorDelivery();
    }

    private record FloorDelivery() implements Delivery {
        public String name() {
            return "Floor delivery";
        }

        public double cost(double weightKg) {
            return 120 + 8 * weightKg;
        }

        public int days() {
            return 1;
        }

        public String executor() {
            return "floor courier Petrenko";
        }
    }
}
