package avachat.webapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class DataManagerAPI {

    private static final Logger log = LoggerFactory.getLogger(DataManagerAPI.class);

    private static final String DIR_NAME =
            "/Users/abhava01/Abhay/repo/Practice/interview/StatesTitle/src/main/resources/";


    private final DataManager dataManager;

    public DataManagerAPI(@Autowired DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping("/data/file/read")
    @ResponseBody
    public String loadFile(@RequestParam String name) {

        String fileName = DIR_NAME + name;

        dataManager.loadFile(fileName);

        return "Successfully read from " + fileName + "\n";
    }


    @PostMapping("/data/process")
    public String processData() {
        dataManager.processData();
        return "Processed all items\n";
    }


}
