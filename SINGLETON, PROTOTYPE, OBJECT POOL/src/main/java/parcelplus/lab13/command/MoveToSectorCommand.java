package parcelplus.lab13.command;

public class MoveToSectorCommand implements Command {

    private final Warehouse warehouse;
    private final String parcelId;
    private final String targetSector;
    private String previousLocation;

    public MoveToSectorCommand(Warehouse warehouse, String parcelId, String targetSector) {
        this.warehouse = warehouse;
        this.parcelId = parcelId;
        this.targetSector = targetSector;
    }

    @Override
    public void execute() {
        previousLocation = warehouse.getLocation(parcelId);
        warehouse.place(parcelId, targetSector);
    }

    @Override
    public void undo() {
        warehouse.place(parcelId, previousLocation);
    }

    @Override
    public String description() {
        return "Move parcel " + parcelId + " to " + targetSector;
    }
}
