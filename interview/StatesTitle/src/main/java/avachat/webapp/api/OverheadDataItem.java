package avachat.webapp.api;

import com.fasterxml.jackson.annotation.JsonSetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverheadDataItem {

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);

    private String countryCode;
    private String commodity;
    private String overheadStr;
    private double overheadValue;

    public OverheadDataItem() {
    }

    public String getCountryCode() {
        return countryCode;
    }

    @JsonSetter("COUNTRY")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCommodity() {
        return commodity;
    }

    @JsonSetter("COMMODITY")
    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getOverheadStr() {
        return overheadStr;
    }

    @JsonSetter("VARIABLE_OVERHEAD")
    public void setOverheadStr(String overheadStr) {
        this.overheadStr = overheadStr;
        try {
            this.overheadValue = Double.parseDouble(overheadStr);
        } catch (Exception e) {
            log.error("Could not parse " + overheadStr);
            this.overheadValue = -1;
        }
    }

    public double getOverheadValue() {
        return overheadValue;
    }

    public void setOverheadValue(double overheadValue) {
        this.overheadValue = overheadValue;
    }

    @Override
    public String toString() {
        return "{" + countryCode + ";" + commodity + ";"
                + overheadStr + ";" + overheadValue + "}";
    }
}
