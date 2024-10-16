package candidateJobOffer;


import candidates.Candidate;
import jobOffers.JobOffer;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "candidate_job_offers")
public class CandidateJobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    @Column(name = "application_date")
    private java.util.Date applicationDate;

    public CandidateJobOffer() {
    }

    public CandidateJobOffer(Candidate candidate, JobOffer jobOffer) {
        this.candidate = candidate;
        this.jobOffer = jobOffer;
        this.applicationDate = new java.util.Date();
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public java.util.Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(java.util.Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
