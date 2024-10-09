package employes.dao;

import employes.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManagerFactory entityManagerFactory;

    public EmployeeDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveEmployee(Employee employee) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(UUID id) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(em.find(Employee.class, id));
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(UUID id) {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
            }
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getDistinctDepartments() {
        try(EntityManager em = entityManagerFactory.createEntityManager()){
            return em.createQuery("SELECT DISTINCT e.department FROM Employee e", String.class).getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

}
