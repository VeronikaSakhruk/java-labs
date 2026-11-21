package parcelplus.lab13.command;

public class LoadIntoVehicleCommand implements Command {

    private final Warehouse warehouse;
    private final String parcelId;
    private final String vehicleId;
    private String previousLocation;

    public LoadIntoVehicleCommand(Warehouse warehouse, String parcelId, String vehicleId) {
        this.warehouse = warehouse;
        this.parcelId = parcelId;
        this.vehicleId = vehicleId;
    }

    @Override
    public void execute() {
        previousLocation = warehouse.getLocation(parcelId);
        warehouse.place(parcelId, "vehicle " + vehicleId);
    }

    @Override
    public void undo() {
        warehouse.place(parcelId, previousLocation);
    }

    @Override
    public String description() {
        return "Load parcel " + parcelId + " into vehicle " + vehicleId;
    }
}
