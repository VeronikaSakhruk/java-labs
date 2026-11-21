package parcelplus.lab13.chain;

public record Shipment(
        String id,
        String sender,
        String receiver,
        String contents,
        double weightKg,
        int lengthCm,
        int widthCm,
        int heightCm,
        boolean international,
        String customsDeclaration,
        boolean lithiumBatteries) {
}
