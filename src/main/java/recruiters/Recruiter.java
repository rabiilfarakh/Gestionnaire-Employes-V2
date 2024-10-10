package recruiters;

import enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "recruiters")
public class Recruiter extends users.User {

    public Recruiter() {
    }

    public Recruiter(String name, String email) {
        super(name, email);
    }

    public Recruiter(String name, String email, String password, String phone, Date birthdate, String cin, Role role) {
        super(name, email, password, phone, birthdate, cin, role);
    }
}
