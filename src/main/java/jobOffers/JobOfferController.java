package jobOffers;

import candidateJobOffer.CandidateJobOffer;
import candidateJobOffer.dao.CandidateJobOfferDAOImpl;
import candidateJobOffer.service.CandidateJobOfferService;
import candidateJobOffer.service.CandidateJobOfferServiceImpl;
import candidates.Candidate;
import enumeration.Contrat;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jobOffers.dao.JobOfferDAOImpl;
import jobOffers.service.JobOfferService;
import jobOffers.service.JobOfferServiceImpl;
import recruiters.Recruiter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@WebServlet("/views/jobOffers")
public class JobOfferController extends HttpServlet {

    private JobOfferService jobOfferService;
    private CandidateJobOfferService candidateJobOfferService;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        jobOfferService = new JobOfferServiceImpl(new JobOfferDAOImpl(entityManagerFactory));
        candidateJobOfferService = new CandidateJobOfferServiceImpl(new CandidateJobOfferDAOImpl(entityManagerFactory));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            String action = request.getParameter("action");
            System.out.println("Action: " + action);

            if ("apply".equals(action)) {
                applyForJobOffer(request, response);
            } else {
                response.sendRedirect("jobOffers");
            }
        } else {
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
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action : "list") {
            case "delete":
                deleteJobOffer(request, response);
                break;
            case "show":
                showJobOffer(request, response);
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

    private void applyForJobOffer(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                UUID jobId = null;
                Candidate candidate = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("id")) {
                            jobId = UUID.fromString(item.getString());
                        }
                    } else {
                        if ("cv".equals(item.getFieldName())) {
                            try (InputStream inputStream = item.getInputStream();
                                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = inputStream.read(buffer)) != -1) {
                                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                                }
                                byte[] fileBytes = byteArrayOutputStream.toByteArray();
                                String cvBase64 = Base64.getEncoder().encodeToString(fileBytes);

                                HttpSession session = request.getSession();
                                candidate = (Candidate) session.getAttribute("user");
                                if (candidate != null) {
                                    candidate.setCv(cvBase64);
                                }
                            }
                        }
                    }
                }

                if (candidate != null && jobId != null) {
                    JobOffer jobOffer = jobOfferService.get(jobId);
                    CandidateJobOffer candidateJobOffer = new CandidateJobOffer(candidate, jobOffer);
                    candidateJobOfferService.save(candidateJobOffer);
                    response.sendRedirect("showJob.jsp?id=" + jobId);  // Redirection vers la page de l'offre après une candidature réussie
                } else {
                    response.sendRedirect("jobOffers");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Erreur lors de la candidature.");
                request.getRequestDispatcher("/views/showJob.jsp?id=" + request.getParameter("id")).forward(request, response);
            }
        } else {
            response.sendRedirect("jobOffers");
        }
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
