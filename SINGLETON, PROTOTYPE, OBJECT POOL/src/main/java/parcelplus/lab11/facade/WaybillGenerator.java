package parcelplus.lab11.facade;

import parcelplus.Log;
import parcelplus.WaybillService;

public class WaybillGenerator {
    public String generate() {
        String number = String.valueOf(WaybillService.getInstance().nextNumber());
        Log.log("WaybillGenerator", "Waybill " + number + " generated");
        return number;
    }
}
