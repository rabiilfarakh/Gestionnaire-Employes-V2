package users;

import employes.dao.EmployeeDAOImpl;
import employes.service.EmployeeService;
import employes.service.EmployeeServiceImpl;
import enumeration.Role;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

@WebServlet("/views/login")
public class UserController extends HttpServlet {

    private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
    private static final String LOGIN_PAGE = "index.jsp";
    private static final String RECRUITER_PAGE = "recruiter.jsp";
    private static final String ADMIN_PAGE = "employees";
    private static final String CANDIDAT_PAGE = "jobOffers";

    private EntityManagerFactory entityManagerFactory;
    private UserService userService;

    @Override
    public void init() throws ServletException {

        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
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
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            User user = userService.login(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                redirectByUserRole(user, response);
            } else {
                handleLoginError(request, response, "Email ou mot de passe incorrect.");
            }
        } catch (Exception e) {
            handleLoginError(request, response, "Une erreur s'est produite lors de la connexion.");
        }
    }

    private void redirectByUserRole(User user, HttpServletResponse response) throws IOException {
        switch (user.getRole()) {
            case Role.Recruter:
                response.sendRedirect(RECRUITER_PAGE);
                break;
            case Role.Admin:
                response.sendRedirect(ADMIN_PAGE);
                break;
            case Role.Candidat:
                response.sendRedirect(CANDIDAT_PAGE);
                break;
            default:
                response.sendRedirect(LOGIN_PAGE);
        }
    }

    private void handleLoginError(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }
}
