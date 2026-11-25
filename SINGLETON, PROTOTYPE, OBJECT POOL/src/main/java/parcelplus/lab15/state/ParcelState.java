package parcelplus.lab15.state;

import parcelplus.Log;

public abstract class ParcelState {

    public abstract String name();

    public void changeAddress(Parcel parcel, String address) {
        refuse("change address", "the address can be changed only before dispatch");
    }

    public void redirect(Parcel parcel, String address) {
        refuse("redirect", "redirection is possible only while the parcel is in transit");
    }

    public void handOver(Parcel parcel) {
        refuse("hand over", "handover is possible only at the branch");
    }

    public void advance(Parcel parcel) {
        refuse("advance", "there is no next stage from this state");
    }

    public void storageDaysPassed(Parcel parcel, int days) {
        refuse("count storage days", "storage days are counted only at the branch");
    }

    private void refuse(String operation, String reason) {
        Log.log(getClass().getSimpleName(), "Cannot " + operation + " in state " + name() + ": " + reason);
    }
}
