package users;

import users.dao.UserDaoImpl;
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


@WebServlet("/views")
public class UserController extends HttpServlet {

    private UserService userService;

    public void init() throws ServletException{
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginContext loginContext = null;
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
            // Authentification réussie
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        } catch (LoginException e) {
            // Authentification échouée
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
