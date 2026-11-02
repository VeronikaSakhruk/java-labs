package parcelplus.lab10.packaging;

public class OversizedKitFactory implements PackagingKitFactory {

    @Override
    public Box createBox() {
        return new OversizedBox();
    }

    @Override
    public Filler createFiller() {
        return new OversizedFiller();
    }

    @Override
    public Label createLabel() {
        return new OversizedLabel();
    }

    private record OversizedBox() implements Box {
        public String type() {
            return "OVERSIZED";
        }

        public String describe() {
            return "wooden crate";
        }
    }

    private record OversizedFiller() implements Filler {
        public String type() {
            return "OVERSIZED";
        }

        public String describe() {
            return "wood wool";
        }
    }

    private record OversizedLabel() implements Label {
        public String type() {
            return "OVERSIZED";
        }

        public String describe() {
            return "label 'OVERSIZED, two-person handling'";
        }
    }
}
