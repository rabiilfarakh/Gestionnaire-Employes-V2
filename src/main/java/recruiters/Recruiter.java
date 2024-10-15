package recruiters;

import enumeration.Role;
import jakarta.persistence.*;
import jobOffers.JobOffer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recruiters")
public class Recruiter extends users.User {

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOffer> jobOffers = new ArrayList<>();

    public Recruiter() {
    }

    public Recruiter(String name, String email) {
        super(name, email);
    }

    public Recruiter(String name, String email, String password, String phone, Date birthdate, String cin, Role role) {
        super(name, email, password, phone, birthdate, cin, role);
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public void addJobOffer(JobOffer jobOffer) {
        jobOffers.add(jobOffer);
        jobOffer.setRecruiter(this);
    }

    public void removeJobOffer(JobOffer jobOffer) {
        jobOffers.remove(jobOffer);
        jobOffer.setRecruiter(null);
    }
}
