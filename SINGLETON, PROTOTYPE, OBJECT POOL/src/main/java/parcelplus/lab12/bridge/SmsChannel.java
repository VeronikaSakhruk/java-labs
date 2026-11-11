package parcelplus.lab12.bridge;

import parcelplus.Log;

public class SmsChannel implements Channel {

    @Override
    public String name() {
        return "SMS";
    }

    @Override
    public void deliver(String recipient, String text) {
        Log.log("SmsChannel", "SMS to " + recipient + ": " + text);
    }
}
