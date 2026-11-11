package parcelplus.lab12.bridge;

import parcelplus.Log;

public class TelegramChannel implements Channel {

    @Override
    public String name() {
        return "Telegram";
    }

    @Override
    public void deliver(String recipient, String text) {
        Log.log("TelegramChannel", "Telegram bot message to " + recipient + ": " + text);
    }
}
