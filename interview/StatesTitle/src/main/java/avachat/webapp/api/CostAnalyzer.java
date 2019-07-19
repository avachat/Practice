package avachat.webapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CostAnalyzer {

    private static final Logger log = LoggerFactory.getLogger(CostAnalyzer.class);

    private final DataManager dataManager;

    public CostAnalyzer(@Autowired DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @GetMapping(value = "/costs", produces = APPLICATION_JSON_VALUE)
    public Object generateCosts(@RequestParam String fruit,
                                @RequestParam double cost,
                                @RequestParam double weight) {

        log.debug("Analyzing cost for " + weight + " of " + fruit + " at " + cost + " per unit");
        return dataManager.generateCostPerCountry(fruit, cost, weight);
    }
}
