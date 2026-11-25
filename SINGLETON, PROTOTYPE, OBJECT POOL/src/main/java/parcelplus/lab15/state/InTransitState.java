package parcelplus.lab15.state;

public class InTransitState extends ParcelState {

    @Override
    public String name() {
        return "IN_TRANSIT";
    }

    @Override
    public void redirect(Parcel parcel, String address) {
        parcel.setAddress(address);
    }

    @Override
    public void advance(Parcel parcel) {
        parcel.setState(new AtBranchState());
    }
}
