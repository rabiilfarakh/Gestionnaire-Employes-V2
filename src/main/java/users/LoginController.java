package users;

import enumeration.Role;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.mindrot.jbcrypt.BCrypt;
import users.dao.UserDAO;
import users.dao.UserDAOImpl;
import users.service.UserService;
import users.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        userService = new UserServiceImpl(new UserDAOImpl(entityManagerFactory));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userService.login(email,password);
            if (user != null) {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                switch (user.getRole()) {
                    case Role.Recruter:
                        response.sendRedirect("/views/recruiter");
                        break;
                    case Role.Admin:
                        response.sendRedirect("/views/employees");
                        break;
                    case Role.Candidat:
                        response.sendRedirect("/views/jobOffers");
                    default:
                        return;
                }
            } else {
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur s'est produite lors de la connexion.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
