package users.dao;

import enumeration.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import org.mindrot.jbcrypt.BCrypt;
import users.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory entityManagerFactory;

    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    @Override
    public void saveUser(User user) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(UUID id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.find(User.class, id);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(UUID id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null; // No user found
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }
}
