package jobOffers;


import enumeration.Contrat;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jobOffers.dao.JobOfferDAOImpl;
import jobOffers.service.JobOfferService;
import jobOffers.service.JobOfferServiceImpl;
import recruiters.Recruiter;
import users.User;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "view":
                viewJobOffer(request, response);
                break;
            case "delete":
                deleteJobOffer(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listJobOffers(request, response);
                break;
        }
    }

    private void listJobOffers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<JobOffer> jobOffers = jobOfferService.getAll();
        request.setAttribute("jobOffers", jobOffers);
        request.getRequestDispatcher("/views/jobs.jsp").forward(request, response);
    }

    private void viewJobOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        try {
            JobOffer jobOffer = jobOfferService.get(id);
            request.setAttribute("jobOffer", jobOffer);
            request.getRequestDispatcher("/jobOfferDetail.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("jobOffers?action=list");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        try {
            JobOffer jobOffer = jobOfferService.get(id);
            request.setAttribute("jobOffer", jobOffer);
            request.getRequestDispatcher("/jobOfferForm.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("jobOffers?action=list");
        }
    }

    private void deleteJobOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        jobOfferService.delete(id);
        response.sendRedirect("jobOffers?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Recruiter recruiter = (Recruiter) session.getAttribute("user");

        String title =  request.getParameter("title");
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Contrat contrat = Contrat.valueOf(request.getParameter("contrat"));

        JobOffer jobOffer = new JobOffer(title,location,description,salary,contrat,recruiter);
        jobOfferService.save(jobOffer);


        response.sendRedirect("jobOffers");
    }
}
