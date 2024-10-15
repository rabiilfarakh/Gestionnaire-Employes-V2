package leaveovers;

import dao.GenericDaoImpl;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class LeaveoverRepository extends GenericDaoImpl<Leaveover, UUID> {

    public LeaveoverRepository(EntityManager entityManager) {
        super(Leaveover.class, entityManager);
    }
}
