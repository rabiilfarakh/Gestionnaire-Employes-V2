package candidates;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
public class Candidate extends users.User{
    private String cv;
}
