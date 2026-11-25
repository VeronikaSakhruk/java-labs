package parcelplus.lab15.state;

public class AcceptedState extends ParcelState {

    @Override
    public String name() {
        return "ACCEPTED";
    }

    @Override
    public void changeAddress(Parcel parcel, String address) {
        parcel.setAddress(address);
    }

    @Override
    public void advance(Parcel parcel) {
        parcel.setState(new InTransitState());
    }
}
