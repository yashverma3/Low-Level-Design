import entities.Ticket;
import factory.Vehicle;
import factory.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();
        Vehicle vehicle = factory.createVehicle("HR06AE8790", "Truck");

        ParkingManager manager = ParkingManager.getInstance();
        Ticket ticket = manager.park(vehicle);
        manager.unpark(ticket);
    }
}
