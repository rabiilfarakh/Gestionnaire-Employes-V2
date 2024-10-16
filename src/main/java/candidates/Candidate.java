package candidates;

import enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.Date;

@Inheritance(strategy = InheritanceType.JOINED)

@Entity
public class Candidate extends users.User{
    private String cv;

    public Candidate(String cv) {
        this.cv = cv;
    }

    public Candidate(String name, String email, String cv) {
        super(name, email);
        this.cv = cv;
    }

    public Candidate(String name, String email, String password, String phone, Date birthdate, String cin, Role role, String cv) {
        super(name, email, password, phone, birthdate, cin, role);
        this.cv = cv;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
