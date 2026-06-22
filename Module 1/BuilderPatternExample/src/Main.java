public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Builder Design Pattern ---");

        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel Core i9-14900K")
                .setRAM("32GB DDR5")
                .setStorage("2TB NVMe SSD")
                .setGraphicsCard(true)
                .setBluetooth(true)
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel Core i3-13100")
                .setRAM("8GB DDR4")
                .setStorage("512GB SATA SSD")
                .build();

        Computer serverPC = new Computer.Builder()
                .setCPU("AMD Ryzen 5 5600G")
                .setRAM("16GB DDR4")
                .setStorage("4TB HDD")
                .setBluetooth(true)
                .build();

        System.out.println("Gaming PC Config:\n" + gamingPC);
        System.out.println("\nOffice PC Config:\n" + officePC);
        System.out.println("\nServer PC Config:\n" + serverPC);
    }
}
