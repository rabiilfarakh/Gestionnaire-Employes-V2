package teste;

import employes.Employee;
import employes.dao.EmployeeDAO;
import employes.dao.EmployeeDAOImpl;
import employes.service.EmployeeService;
import employes.service.EmployeeServiceImpl;
import enumeration.Role;
import jakarta.persistence.*;
import users.User;
import users.dao.UserDAO;
import users.dao.UserDAOImpl;
import users.service.UserService;
import users.service.UserServiceImpl;

import java.util.List;

public class EmployeeTest {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");


        UserDAO userDAO = new UserDAOImpl(entityManagerFactory);
        UserService userService = new UserServiceImpl(userDAO);




        User user = userService.login("oussama@gmail.com","oussama");
        if(user.getRole().equals(Role.Recruter)) {
            System.out.println(user.getRole());
        }
    }
}
