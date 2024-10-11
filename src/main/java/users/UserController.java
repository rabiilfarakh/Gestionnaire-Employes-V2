package users;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import users.dao.UserDAOImpl;
import users.service.UserService;
import users.service.UserServiceImpl;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WEB-INF/views")
public class UserController extends HttpServlet {

    private UserService userService;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginContext loginContext;
        try {
            loginContext = new LoginContext("MyLoginModule", new CallbackHandler() {
                @Override
                public void handle(Callback[] callbacks) throws IOException {
                    for (Callback callback : callbacks) {
                        if (callback instanceof NameCallback) {
                            ((NameCallback) callback).setName(email);
                        } else if (callback instanceof PasswordCallback) {
                            ((PasswordCallback) callback).setPassword(password.toCharArray());
                        }
                    }
                }
            });
            loginContext.login();
            // Successful authentication
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        } catch (LoginException e) {
            // Failed authentication
            request.setAttribute("errorMessage", "Authentication failed: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}