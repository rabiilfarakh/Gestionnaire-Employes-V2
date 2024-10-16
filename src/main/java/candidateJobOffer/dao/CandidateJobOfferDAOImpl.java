package candidateJobOffer.dao;

import candidateJobOffer.CandidateJobOffer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CandidateJobOfferDAOImpl implements CandidateJobOfferDAO{
    private EntityManagerFactory entityManagerFactory;

    public CandidateJobOfferDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(CandidateJobOffer candidateJobOffer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(candidateJobOffer);
        em.getTransaction().commit();
        em.close();
    }
}
