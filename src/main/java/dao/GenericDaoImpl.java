package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {

    protected EntityManager entityManager;
    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }

    @Override
    public Optional<T> findByEmail(String email) {
        TypedQuery<T> query = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.email = :email", entityClass);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<T> search(String query) {
        TypedQuery<T> typedQuery = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name LIKE :query", entityClass);
        typedQuery.setParameter("query", "%" + query + "%");
        return typedQuery.getResultList();
    }
}
