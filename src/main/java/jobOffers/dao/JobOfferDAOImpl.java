package jobOffers.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jobOffers.JobOffer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JobOfferDAOImpl implements JobOfferDAO {

    private EntityManagerFactory entityManagerFactory;

    public JobOfferDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(JobOffer jobOffer) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            em.persist(jobOffer);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<JobOffer> getAll() {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            return em.createQuery("SELECT e FROM JobOffer e", JobOffer.class).getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<JobOffer> get(UUID id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(em.find(JobOffer.class, id));
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(JobOffer jobOffer) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            em.merge(jobOffer);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UUID id) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            JobOffer jobOffer = em.find(JobOffer.class, id);
            if (jobOffer != null) {
                em.remove(jobOffer);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
