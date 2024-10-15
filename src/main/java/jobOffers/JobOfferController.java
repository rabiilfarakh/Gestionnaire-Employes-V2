package jobOffers;

import enumeration.Contrat;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jobOffers.dao.JobOfferDAOImpl;
import jobOffers.service.JobOfferService;
import jobOffers.service.JobOfferServiceImpl;
import recruiters.Recruiter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/views/jobOffers")
public class JobOfferController extends HttpServlet {

    private JobOfferService jobOfferService;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        jobOfferService = new JobOfferServiceImpl(new JobOfferDAOImpl(entityManagerFactory));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "") {
            case "save":
                saveJobOffer(request, response);
                break;
            case "show":
                showJobOffer(request, response);
                break;
            default:
                response.sendRedirect("jobOffers?action=list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "list") {
            case "delete":
                deleteJobOffer(request, response);
                break;
            case "list":
            default:
                listJobOffers(request, response);
                break;
        }
    }

    // ------------ Méthodes privées ------------

    private void listJobOffers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<JobOffer> jobOffers = jobOfferService.getAll();
        request.setAttribute("jobOffers", jobOffers);
        request.getRequestDispatcher("/views/jobs.jsp").forward(request, response);
    }


    private void showJobOffer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = getUUIDFromRequest(request, "id");
        try {
            JobOffer jobOffer = jobOfferService.get(id);
            request.setAttribute("jobOffer", jobOffer);
            request.getRequestDispatcher("showJob.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("jobOffers?action=list");
        }
    }

    private void saveJobOffer(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Recruiter recruiter = (Recruiter) session.getAttribute("user");

        try {
            String title = request.getParameter("title");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            double salary = Double.parseDouble(request.getParameter("salary"));
            Contrat contrat = Contrat.valueOf(request.getParameter("contrat"));

            JobOffer jobOffer = new JobOffer(title, location, description, salary, contrat, recruiter);
            jobOfferService.save(jobOffer);
            response.sendRedirect("jobOffers?action=list");
        } catch (Exception e) {
            request.setAttribute("error", "Erreur lors de l'enregistrement de l'offre.");
            request.getRequestDispatcher("/views/jobForm.jsp").forward(request, response);
        }
    }

    private void deleteJobOffer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = getUUIDFromRequest(request, "id");
        jobOfferService.delete(id);
        response.sendRedirect("jobOffers?action=list");
    }

    // ------------ Utilitaire ------------

    private UUID getUUIDFromRequest(HttpServletRequest request, String paramName) {
        return UUID.fromString(request.getParameter(paramName));
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
