package employes.dao;

import employes.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {

    void saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(UUID id);

    void updateEmployee(Employee employee);

    void deleteEmployee(UUID id);

}
