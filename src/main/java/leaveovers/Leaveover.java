package leaveovers;

import enumeration.LeaveoverStatus;
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Leaveover {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.DATE)
    private Date dateValidite;

    private String document;

    @Enumerated(EnumType.STRING)
    private LeaveoverStatus status;

    public Leaveover() {
    }

    public Leaveover(Date startDate, Date endDate, Date dateValidite, String document, LeaveoverStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateValidite = dateValidite;
        this.document = document;
        this.status = status;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LeaveoverStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveoverStatus status) {
        this.status = status;
    }
}
