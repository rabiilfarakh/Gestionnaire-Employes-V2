import users.User;
import users.dao.UserDaoImpl;
import enumeration.Role;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        User testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
        testUser.setPhone("1234567890");
        testUser.setBirthdate(new Date());
        testUser.setCin("CIN123456");
        testUser.setRole(Role.Employee);

        userDao.saveUser(testUser);
        System.out.println("Utilisateur sauvegardé : " + testUser.getName());


        User fetchedUser = userDao.getUserById(testUser.getId());


        if (fetchedUser != null) {
            System.out.println("Utilisateur récupéré : " + fetchedUser.getName());
            System.out.println("Email : " + fetchedUser.getEmail());
            System.out.println("Rôle : " + fetchedUser.getRole());
        } else {
            System.out.println("L'utilisateur n'a pas pu être récupéré.");
        }



    }
}
