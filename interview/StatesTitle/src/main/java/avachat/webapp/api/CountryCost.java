package avachat.webapp.api;

public class CountryCost {

    private final String name;
    private final double cost;

    public CountryCost(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
