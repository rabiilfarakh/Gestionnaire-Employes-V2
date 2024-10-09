package employes.service;

import employes.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(UUID id);

    void updateEmployee(Employee employee);

    void deleteEmployee(UUID id);
}
