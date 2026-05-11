package factory;

public class VehicleFactory {
    public Vehicle createVehicle(String licenseNumber, String type) {
        switch(type) {
            case "Car": return new Car(licenseNumber);
            case "Bike": return new Bike(licenseNumber);
            case "Truck": return new Truck(licenseNumber);
            default: return null;
        }
    }
}