package parcelplus.lab10.packaging;

public class ColdKitFactory implements PackagingKitFactory {

    @Override
    public Box createBox() {
        return new ColdBox();
    }

    @Override
    public Filler createFiller() {
        return new ColdFiller();
    }

    @Override
    public Label createLabel() {
        return new ColdLabel();
    }

    private record ColdBox() implements Box {
        public String type() {
            return "COLD";
        }

        public String describe() {
            return "insulated thermal box";
        }
    }

    private record ColdFiller() implements Filler {
        public String type() {
            return "COLD";
        }

        public String describe() {
            return "gel cooling packs";
        }
    }

    private record ColdLabel() implements Label {
        public String type() {
            return "COLD";
        }

        public String describe() {
            return "label 'KEEP AT 2-8 C'";
        }
    }
}
