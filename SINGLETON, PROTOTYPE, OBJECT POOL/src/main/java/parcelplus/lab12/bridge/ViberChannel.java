package parcelplus.lab12.bridge;

import parcelplus.Log;

public class ViberChannel implements Channel {

    @Override
    public String name() {
        return "Viber";
    }

    @Override
    public void deliver(String recipient, String text) {
        Log.log("ViberChannel", "Viber message to " + recipient + ": " + text);
    }
}
