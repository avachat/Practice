package avachat.webapp.api;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@RestController
public class DataManager {

    private static final String DIR_NAME =
            "/Users/abhava01/Abhay/repo/Practice/interview/StatesTitle/src/main/resources/";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);


    public static class OverheadDataItem {
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


    @PostMapping("/data/file/read")
    @ResponseBody
    public String loadFile(@RequestParam String name) {

        String fileName = DIR_NAME + name;

        String statusMessage = "";

        try {
            InputStream file = new FileInputStream(fileName);
            List<OverheadDataItem> overheadDataItemList =
                    OBJECT_MAPPER.readValue(file, new TypeReference<List<OverheadDataItem>>(){});

            log.info(String.valueOf(overheadDataItemList));

            statusMessage = "Read from file " + fileName + "\n";
        } catch (FileNotFoundException e) {
            log.error("Could not read file " + fileName, e);
            statusMessage = e.getMessage();
        } catch (JsonParseException e) {
            log.error("Could not parse JSON", e);
            statusMessage = e.getMessage();
        } catch (JsonMappingException e) {
            log.error("Could not map json");
            statusMessage = e.getMessage();
        } catch (IOException e) {
            log.error("General IO Exception", e);
            statusMessage = e.getMessage();
        }

        return statusMessage;

    }
}
