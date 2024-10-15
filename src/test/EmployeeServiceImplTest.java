package employes.service;

import employes.Employee;
import employes.dao.EmployeeDAO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDAO employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private UUID employeeId;

    @BeforeEach
    void setUp() {
        employeeId = UUID.randomUUID();
        employee = new Employee("John Doe", "john@example.com", "Developer", "Engineering", 60000.0);
    }

    @Test
    void testSaveEmployee() {
        employeeService.saveEmployee(employee);
        verify(employeeDao, times(1)).saveEmployee(employee);
    }

    @Test
    void testGetAllEmployees() {
        when(employeeDao.getAllEmployees()).thenReturn(Collections.singletonList(employee));

        List<Employee> employees = employeeService.getAllEmployees();

        verify(employeeDao, times(1)).getAllEmployees();
        assertEquals(1, employees.size());
        assertEquals(employee, employees.get(0));
    }

    @Test
    void testGetEmployeeByIdFound() {
        when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(employeeId);

        verify(employeeDao, times(1)).getEmployeeById(employeeId);
        assertEquals(employee, foundEmployee);
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeDao.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.getEmployeeById(employeeId);
        });

        verify(employeeDao, times(1)).getEmployeeById(employeeId);
        assertEquals("Employee not found with id: " + employeeId, exception.getMessage());
    }

    @Test
    void testUpdateEmployee() {
        employeeService.updateEmployee(employee);
        verify(employeeDao, times(1)).updateEmployee(employee);
    }

    @Test
    void testDeleteEmployee() {
        employeeService.deleteEmployee(employeeId);
        verify(employeeDao, times(1)).deleteEmployee(employeeId);
    }

    @Test
    void testGetDistinctDepartments() {
        List<String> departments = Collections.singletonList("Engineering");
        when(employeeDao.getDistinctDepartments()).thenReturn(departments);

        List<String> distinctDepartments = employeeService.getDistinctDepartments();

        verify(employeeDao, times(1)).getDistinctDepartments();
        assertEquals(departments, distinctDepartments);
    }
}
