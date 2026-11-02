package parcelplus.lab10.delivery;

public class BranchPickupDeliveryProcess extends DeliveryProcess {

    @Override
    protected Delivery createDelivery() {
        return new BranchPickupDelivery();
    }

    private record BranchPickupDelivery() implements Delivery {
        public String name() {
            return "Branch pickup";
        }

        public double cost(double weightKg) {
            return 30 + 2 * weightKg;
        }

        public int days() {
            return 3;
        }

        public String executor() {
            return "branch #3";
        }
    }
}
