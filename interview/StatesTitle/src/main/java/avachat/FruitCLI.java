package avachat;

import avachat.webapp.api.CountryCost;
import avachat.webapp.api.DataManager;
import avachat.webapp.api.OverheadDataItem;

import java.util.List;

public class FruitCLI {

    private static final String DIR_NAME =
            "/Users/abhava01/Abhay/repo/Practice/interview/StatesTitle/src/main/resources/";
    private static final String FILE_NAME = "sample.json";

    public static void main (String[] args) {

        DataManager dataManager = new DataManager();

        System.out.print("Starting Fruit CLI");

        // input
        // fruitname price volume
        if (args.length != 3) {
            System.out.println("Usage : CLI fruitName price volume");
        }

        String fruitName = args[0];
        String priceStr = args[1];
        String volumeStr = args[2];

        double price = Double.parseDouble(priceStr);
        double volume = Double.parseDouble(volumeStr);

        dataManager.loadFile(DIR_NAME + FILE_NAME);
        dataManager.processData();

        List<CountryCost> costList = dataManager.generateCostPerCountry(fruitName, price, volume);

        // countrycode totalcost
        for (CountryCost countryCost : costList) {
            System.out.println(countryCost.getName() + " " + countryCost.getCost());
        }
    }
}
