package recruiters.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jobOffers.JobOffer;
import recruiters.Recruiter;

public class RecruiterDAOImpl implements RecruiterDAO {

    private EntityManagerFactory entityManagerFactory;

    public RecruiterDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Recruiter recruiter) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            em.persist(recruiter);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }
}
