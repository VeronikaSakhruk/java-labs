package parcelplus.lab15.state;

import parcelplus.Log;

public class Parcel {

    private final String id;
    private String address;
    private ParcelState state = new CreatedState();

    public Parcel(String id, String address) {
        this.id = id;
        this.address = address;
        Log.log("Parcel " + id, "Created in state " + state.name() + ", address " + address);
    }

    public synchronized void changeAddress(String address) {
        state.changeAddress(this, address);
    }

    public synchronized void redirect(String address) {
        state.redirect(this, address);
    }

    public synchronized void handOver() {
        state.handOver(this);
    }

    public synchronized void advance() {
        state.advance(this);
    }

    public synchronized void storageDaysPassed(int days) {
        state.storageDaysPassed(this, days);
    }

    void setAddress(String address) {
        Log.log("Parcel " + id, "Address changed from " + this.address + " to " + address);
        this.address = address;
    }

    void setState(ParcelState newState) {
        Log.log("Parcel " + id, "State " + state.name() + " -> " + newState.name());
        this.state = newState;
    }
}
