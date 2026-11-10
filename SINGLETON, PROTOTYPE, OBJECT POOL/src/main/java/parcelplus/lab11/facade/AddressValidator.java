package parcelplus.lab11.facade;

import parcelplus.Log;

public class AddressValidator {
    public boolean validate(String address) {
        boolean valid = address != null && !address.isBlank();
        Log.log("AddressValidator", "Address '" + address + "' valid: " + valid);
        return valid;
    }
}
