package jobOffers;

import enumeration.Contrat;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "jobOffers")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double salary;

    @Enumerated(EnumType.STRING)
    private Contrat contrat;


    public JobOffer() {
    }


    public JobOffer(String title, String location, String description, double salary, Contrat contrat) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.salary = salary;
        this.contrat = contrat;
    }

    // Getters et Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
