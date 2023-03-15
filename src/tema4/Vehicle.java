package tema4;
/**
 * An interface that defines the methods that all vehicles must have
 */
public interface Vehicle {
    public void start();
    public void stop();
    public void drive(double distance);
    public void shiftGear(int gears);
}
