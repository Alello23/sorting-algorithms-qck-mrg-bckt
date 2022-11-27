package src;

public class Vessel {


    public int id;
    public String name;
    public String type;
    public float weight;
    public float length;
    public int capacity;
    public int n_competitions;
    public String status;
    public int speed;
    public String center;

    private final double WEIGHT_FACTOR = 0.20;
    private final double LENGTH_FACTOR = 0.10;
    private final double CAPACITY_FACTOR = 0.40;
    private final double SPEED_FACTOR = 0.30;

    public Vessel (int id, String name, String type, float weight, float length, int capacity, int n_competitions, String status, int speed, String center) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.length = length;
        this.capacity = capacity;
        this.n_competitions = n_competitions;
        this.status = status;
        this.speed = speed;
        this.center = center;
    }

    public double getCapabilitiesRating() {

        double normCapacity = normalize(capacity, 0, 10);
        double normSpeed = normalize(speed, 0, 50);
        double normLength = normalize(length, 0, 8);

        double normWeight = normalize(weight, 300, 0);

        return  (normLength * LENGTH_FACTOR) + (normCapacity * CAPACITY_FACTOR) + (normSpeed * SPEED_FACTOR) + (normWeight * WEIGHT_FACTOR);
    }

    private double normalize(double value, double minValue, double maxValue) {
        return 1 - ( (value - maxValue) / (minValue - maxValue) );
    }
}
