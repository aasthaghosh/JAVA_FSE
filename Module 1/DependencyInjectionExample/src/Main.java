public class Main {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        String customer = service.getCustomer("1");
        System.out.println("Customer retrieved: " + customer);
    }
}
