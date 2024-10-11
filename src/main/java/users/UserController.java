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

        private EntityManagerFactory entityManagerFactory;
        private UserService userService;

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
                            response.sendRedirect("recruiter.jsp");
                            break;
                        case Role.Admin:
                            response.sendRedirect("employees");
                            break;
                        case Role.Candidat:
                            response.sendRedirect("jobOffers");
                            break;
                        default:
                            return;
                    }
                } else {
                    request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Une erreur s'est produite lors de la connexion.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
          }
    }