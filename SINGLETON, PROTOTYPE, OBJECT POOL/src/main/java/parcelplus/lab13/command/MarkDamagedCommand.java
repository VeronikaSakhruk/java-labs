package parcelplus.lab13.command;

public class MarkDamagedCommand implements Command {

    private final Warehouse warehouse;
    private final String parcelId;
    private boolean previousState;

    public MarkDamagedCommand(Warehouse warehouse, String parcelId) {
        this.warehouse = warehouse;
        this.parcelId = parcelId;
    }

    @Override
    public void execute() {
        previousState = warehouse.isDamaged(parcelId);
        warehouse.setDamaged(parcelId, true);
    }

    @Override
    public void undo() {
        warehouse.setDamaged(parcelId, previousState);
    }

    @Override
    public String description() {
        return "Mark parcel " + parcelId + " as damaged";
    }
}
