public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        if ("1".equals(id)) {
            return "John Doe";
        }
        return "Customer Not Found";
    }
}
