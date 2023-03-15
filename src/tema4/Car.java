package tema4;
/**
 * Create an applicaton based on the following requirements:
 * All Vehicles have the following behaviour: they can be started, stopped, drived for a number of kms
 * Implement a hierarchy of Car classes where:
 * Car is of type Vehicle
 * Properties of a Car model which cannot change: fuelTankSize, gears, consumptionPer100Km
 * Properties that are configurable: availableFuel, tireSize
 * Properties that are instance specific: chassisNumber
 * consumptionPer100Km is the consumption of the car per 100 KM with the smallest tire setting in the first gear
 * Tasks:
 * Implement the classes, abstract classes and interfaces according to the given information
 * Create at least 2 car brands with 2 models for each
 * Make sure each car has different consumption
 * Some cars might have a decrease in consumption every time they shift up and the same increase when they shift down
 * Some cars might have an increase in consumption with larger tires
 */
public class Car implements Vehicle {
    private double consumption;
    double odometer;
    private int fuelTankSize;
    private String fuelType;
    private int maxGear;
    private int currentGear;
    private float consumptionPer100Km = 4.7f;
    private double availableFuel;
    private int tireSize;
    private String chassisNumber;
    public Car(int fuelTankSize, String fuelType, int maxGear, float consumptionPer100Km, double availableFuel, String chassisNumber) {
        this.fuelTankSize = fuelTankSize;
        this.fuelType = fuelType;
        this.maxGear = maxGear;
        this.consumptionPer100Km = consumptionPer100Km;
        this.availableFuel = availableFuel;
        this.tireSize = 15;
        this.chassisNumber = chassisNumber;
    }
    @Override
    public String toString() {
        return "Tank size:" + this.fuelTankSize +
                ", Fuel type:" + this.fuelType +
                ", Gears:" + this.maxGear +
                ", Consumption:" + this.consumptionPer100Km +
                ", Available fuel:" + this.availableFuel +
                ", Chassis Nr:" + this.chassisNumber;
    }
    /**
     * During a start() and a stop() the car keeps track of how much it has consumed
     * On every start() the consumption stats are reset
     */
    @Override
    public void start() {
        System.out.println("Car " + this.chassisNumber + " has started with available fuel: " + this.availableFuel);
        this.consumption = 0;
        this.odometer = 0;
    }
    public void stop() {
        System.out.println("Car " + this.chassisNumber + " has stopped. Real average consumption per 100 Km: " +
                getAverageFuelConsumption() + " Remaining fuel: " + String.format("%.4f", this.availableFuel));
    }
    /**
     * A car can be driven for N kms in different gears
     *
     * @param distance The odometer (total km) is increased with the distance and the consumption is calculated based on the estimated
     *                 consumption, and slightly affected by the current status of the car (gear and tire size)
     */
    public void drive(double distance) {
        this.odometer += distance;
        this.consumption += distance * this.consumptionPer100Km / 100 * this.consumptionMultiplier();
        this.availableFuel -= distance * this.consumptionPer100Km / 100 * this.consumptionMultiplier();
        System.out.println("Car " + this.chassisNumber + " has moved " + distance + " km in gear " + this.currentGear +
                " with tire size " + this.tireSize +
                ". Odometer= " + String.format("%.4f", this.odometer) + " and the total consumption= " +
                String.format("%.6f", this.consumption));
    }
    /**
     * Each car has different consumption based on the model
     *
     * @return
     */
    private double consumptionMultiplier() {
        switch (this.getClass().getSimpleName()) {
            case "Logan":
                return (double) this.tireSize / 15 - (this.currentGear - 1) * 0.05;
            case "Duster":
                return this.tireSize / 15;
            case "VWGolf":
                return (double) this.tireSize / 15 - (this.currentGear - 1) * 0.10;
            default:
                return 1;
        }
    }
    public void shiftGear(int gear) {
        if (gear > this.maxGear) {
            gear = this.maxGear;
        }
        if (gear < 1) {
            gear = 1;
        }
        this.currentGear = gear;
    }
    public void changeTires(int tireSize) {
        this.tireSize = tireSize;
    }
    public float getAverageFuelConsumption() {
        return (float) (100 * this.consumption / this.odometer);
    }
    public float getAvailableFuel() {
        return (float) this.availableFuel;
    }
}