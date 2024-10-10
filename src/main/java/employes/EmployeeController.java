package employes;

import employes.dao.EmployeeDAOImpl;
import employes.service.EmployeeService;
import employes.service.EmployeeServiceImpl;
import enumeration.Role;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.mindrot.jbcrypt.BCrypt;
import users.User;
import users.dao.UserDAOImpl;
import users.service.UserService;
import users.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/views/employees")
public class EmployeeController extends HttpServlet {
    private static final String VIEW_EMPLOYES = "/views/employees.jsp";
    private static final String VIEW_EDIT_EMPLOYEE = "/views/editEmployee.jsp";

    private EntityManagerFactory entityManagerFactory;
    private EmployeeService employeeService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        employeeService = new EmployeeServiceImpl(new EmployeeDAOImpl(entityManagerFactory));

        // Initialize userService here
        userService = new UserServiceImpl(new UserDAOImpl(entityManagerFactory));
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action != null ? action : "") {
            case "edit":
                String id = request.getParameter("id");
                UUID uuid = UUID.fromString(id);
                Employee employee = employeeService.getEmployeeById(uuid);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher(VIEW_EDIT_EMPLOYEE).forward(request, response);
                break;
            case "filter":
                //filterEmployees(request, response);
                break;
            default:
                listEmployees(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action != null ? action : "") {
            case "add":
                addEmployee(request, response);
                break;
            case "edit":
                //updateEmployee(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    //addEmployee
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String birthdateStr = request.getParameter("birthdate");
            Date birthdate = null;

            if (birthdateStr != null && !birthdateStr.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthdate = dateFormat.parse(birthdateStr);
            }

            String cin = request.getParameter("cin");
            String position = request.getParameter("position");
            String department = request.getParameter("department");
            Double salary = null;
            String salaryStr = request.getParameter("salary");

            if (salaryStr != null && !salaryStr.isEmpty()) {
                salary = Double.parseDouble(salaryStr);
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            Employee employee = new Employee(name, email, hashedPassword, phone, birthdate, cin, Role.Employee, position, department, salary);

            employeeService.saveEmployee(employee);

            response.sendRedirect("employees");

        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de la conversion de la date.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de la conversion du salaire.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur est survenue.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    //listEmployees
    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            //List<String> distinctDepartments = employeeService.getDistinctDepartments();
            request.setAttribute("employees", employees);
            //request.setAttribute("distinctDepartments", distinctDepartments);
            request.getRequestDispatcher(VIEW_EMPLOYES).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
