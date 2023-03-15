package tema4;
/** Dacia is a car brand. Can't be instantiated.
 *
 */
public abstract class Dacia extends Car {
    public Dacia(int fuelTankSize, String fuelType, int gear, float consumptionPer100Km, double availableFuel, String chassisNumber) {
        super(fuelTankSize, fuelType, gear, consumptionPer100Km, availableFuel, chassisNumber);
    }
}
