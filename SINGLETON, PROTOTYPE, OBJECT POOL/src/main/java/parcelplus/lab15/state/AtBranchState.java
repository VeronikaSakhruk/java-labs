package parcelplus.lab15.state;

import parcelplus.Log;

public class AtBranchState extends ParcelState {

    private static final int MAX_STORAGE_DAYS = 5;

    @Override
    public String name() {
        return "AT_BRANCH";
    }

    @Override
    public void handOver(Parcel parcel) {
        parcel.setState(new DeliveredState());
    }

    @Override
    public void storageDaysPassed(Parcel parcel, int days) {
        if (days >= MAX_STORAGE_DAYS) {
            Log.log("AtBranchState", days + " days of storage, the limit of " + MAX_STORAGE_DAYS
                    + " days is reached, returning to sender");
            parcel.setState(new ReturnedState());
        } else {
            Log.log("AtBranchState", days + " days of storage, the parcel is kept at the branch");
        }
    }
}
