package avachat.webapp.api;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class DataManager {

    private static final Logger log = LoggerFactory.getLogger(DataManager.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    // all items read from file
    private OverheadDataItem[] allOverheadDataItems = null;

    // keys : commodity name : List of items sorted by price
    private Map<String, List<OverheadDataItem>> sortedItems = new HashMap<>();

    // has the data been initialized
    private boolean isFileLoaded = false;
    private boolean isFileProcessed = false;



    synchronized public void loadFile(@RequestParam String fileName) {

        if (isFileLoaded) {
            throw new RuntimeException("Data has already been read ");
        }


        try {
            InputStream file = new FileInputStream(fileName);

            allOverheadDataItems = OBJECT_MAPPER.readValue(file, OverheadDataItem[].class);
            log.debug(Arrays.toString(allOverheadDataItems));

            isFileLoaded = true;

            //List<OverheadDataItem> overheadDataItemList =
                    //OBJECT_MAPPER.readValue(file, new TypeReference<List<OverheadDataItem>>(){});
            //log.info(String.valueOf(overheadDataItemList));

        } catch (FileNotFoundException e) {
            String errorStr = "Could not read file " + fileName;
            log.error(errorStr, e);
            throw new RuntimeException(errorStr,e);
        } catch (IOException e) {
            String errorStr = "Could not parse JSON ";
            log.error(errorStr, e);
            throw new RuntimeException(errorStr, e);
        }

    }


    synchronized public void processData() {

        if (! isFileLoaded) {
            throw new RuntimeException("File has not been loaded yet");
        }

        if ((allOverheadDataItems == null) || (allOverheadDataItems.length ==0)) {
            throw new RuntimeException("No data to process");
        }

        if (isFileProcessed) {
            throw new RuntimeException("File has already been processed");
        }

        // Get all items sorted in decreasing order by overheadValue
        // So adding to the list will keep the lists in sorted order
        Arrays.sort(allOverheadDataItems,
                (a,b) -> Double.compare(b.getOverheadValue(), a.getOverheadValue()));
        //log.debug(Arrays.toString(allOverheadDataItems));

        sortedItems = new HashMap<>();

        for (OverheadDataItem item : allOverheadDataItems) {

            String name = item.getCommodity();

            if (!sortedItems.containsKey(name)) {
                sortedItems.put(name, new ArrayList<>());
            }
            List<OverheadDataItem> itemList = sortedItems.get(name);

            itemList.add(item);
        }

        isFileProcessed = true;

        log.debug(sortedItems.toString());
    }


    public List<CountryCost> generateCostPerCountry(String fruit, double price, double weight) {

        if (! isFileProcessed) {
            throw new RuntimeException("File has not been processed yet");
        }

        if (! sortedItems.containsKey(fruit)) {
            throw new RuntimeException("Fruit " + fruit + " does not exist");
        }

        List<OverheadDataItem> itemList = sortedItems.get(fruit);

        List<CountryCost> costs = new ArrayList<>(itemList.size());

        for (OverheadDataItem item : itemList) {
            String countryCode = item.getCountryCode();
            double overhead = item.getOverheadValue();
            double cost = overhead * price * weight;
            CountryCost countryCost = new CountryCost(countryCode, cost);
            costs.add(countryCost);
        }

        return costs;
    }


}
