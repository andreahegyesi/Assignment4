package tema4;
/**
 * Volkswagen is a car brand. Can't be instantiated.
 */
public abstract class Volkswagen extends Car {
    public Volkswagen(int fuelTankSize, String fuelType, int gear, float consumptionPer100Km, double availableFuel, String chassisNumber) {
        super(fuelTankSize, fuelType, gear, consumptionPer100Km, availableFuel, chassisNumber);
    }
}
