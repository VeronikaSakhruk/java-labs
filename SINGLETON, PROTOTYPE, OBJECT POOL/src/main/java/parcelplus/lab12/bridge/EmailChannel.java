package parcelplus.lab12.bridge;

import parcelplus.Log;

public class EmailChannel implements Channel {

    @Override
    public String name() {
        return "e-mail";
    }

    @Override
    public void deliver(String recipient, String text) {
        Log.log("EmailChannel", "E-mail to " + recipient + ", subject \"Parcel+ notification\", body: " + text);
    }
}
