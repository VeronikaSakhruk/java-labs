package parcelplus.lab10.packaging;

public class StandardKitFactory implements PackagingKitFactory {

    @Override
    public Box createBox() {
        return new StandardBox();
    }

    @Override
    public Filler createFiller() {
        return new StandardFiller();
    }

    @Override
    public Label createLabel() {
        return new StandardLabel();
    }

    private record StandardBox() implements Box {
        public String type() {
            return "STANDARD";
        }

        public String describe() {
            return "single-wall cardboard box";
        }
    }

    private record StandardFiller() implements Filler {
        public String type() {
            return "STANDARD";
        }

        public String describe() {
            return "paper crumple";
        }
    }

    private record StandardLabel() implements Label {
        public String type() {
            return "STANDARD";
        }

        public String describe() {
            return "standard shipping label";
        }
    }
}
