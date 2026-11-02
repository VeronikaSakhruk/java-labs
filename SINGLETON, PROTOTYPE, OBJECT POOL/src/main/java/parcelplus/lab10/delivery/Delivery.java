package parcelplus.lab10.delivery;

public interface Delivery {

    String name();

    double cost(double weightKg);

    int days();

    String executor();
}
