package exercise4;

public class EmployeeManagement {
    private Employee[] employees;
    private int count;

    public EmployeeManagement(int capacity) {
        this.employees = new Employee[capacity];
        this.count = 0;
    }

    public boolean add(Employee employee) {
        if (count >= employees.length) {
            return false;
        }
        employees[count++] = employee;
        return true;
    }

    public Employee search(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverse() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i].getEmployeeId() + " - " + employees[i].getName());
        }
    }

    public boolean delete(String employeeId) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        for (int i = index; i < count - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[count - 1] = null;
        count--;
        return true;
    }
}
