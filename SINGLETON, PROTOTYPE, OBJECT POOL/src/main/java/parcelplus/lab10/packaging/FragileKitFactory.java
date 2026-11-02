package parcelplus.lab10.packaging;

public class FragileKitFactory implements PackagingKitFactory {

    @Override
    public Box createBox() {
        return new FragileBox();
    }

    @Override
    public Filler createFiller() {
        return new FragileFiller();
    }

    @Override
    public Label createLabel() {
        return new FragileLabel();
    }

    private record FragileBox() implements Box {
        public String type() {
            return "FRAGILE";
        }

        public String describe() {
            return "double-wall reinforced box";
        }
    }

    private record FragileFiller() implements Filler {
        public String type() {
            return "FRAGILE";
        }

        public String describe() {
            return "foam inserts";
        }
    }

    private record FragileLabel() implements Label {
        public String type() {
            return "FRAGILE";
        }

        public String describe() {
            return "label 'FRAGILE, handle with care'";
        }
    }
}
