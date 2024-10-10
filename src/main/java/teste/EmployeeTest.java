package teste;

import employes.Employee;
import employes.dao.EmployeeDAO;
import employes.dao.EmployeeDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeeTest {

    public static void main(String[] args) {
        // Initialize EntityManagerFactory with the persistence unit name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Pass the initialized EntityManagerFactory to EmployeeDAOImpl
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(entityManagerFactory);

        // Retrieve all employees
        List<Employee> result = employeeDAO.getAllEmployees();

        // Check if result is not null and not empty
        if (result != null && !result.isEmpty()) {
            for (Employee employee : result) {
                // Display employee details
                System.out.println("Role: " + employee.getRole());
                System.out.println("Birthdate: " + employee.getBirthdate());
                System.out.println("Cin: " + employee.getCin());
                System.out.println("Name: " + employee.getName());
                System.out.println("Email: " + employee.getEmail());
                System.out.println("Pwd: " + employee.getPassword());
                System.out.println("Phone: " + employee.getPhone());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Department: " + employee.getDepartment());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No data found or an error occurred.");
        }

        // Close the EntityManagerFactory after use
        entityManagerFactory.close();
    }
}
