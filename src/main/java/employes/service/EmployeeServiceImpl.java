package employes.service;

import employes.Employee;
import employes.dao.EmployeeDao;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(UUID id) {
        return employeeDao.getEmployeeById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeDao.deleteEmployee(id);
    }
}
