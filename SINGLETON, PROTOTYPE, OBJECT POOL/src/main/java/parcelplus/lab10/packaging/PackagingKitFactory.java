package parcelplus.lab10.packaging;

public interface PackagingKitFactory {

    Box createBox();

    Filler createFiller();

    Label createLabel();
}
