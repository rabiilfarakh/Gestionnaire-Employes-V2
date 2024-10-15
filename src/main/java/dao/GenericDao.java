package dao;


import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {

    /**
     * Enregistre une entité dans la base de données.
     *
     * @param entity l'entité à enregistrer
     */
    void save(T entity);

    /**
     * Met à jour une entité dans la base de données.
     *
     * @param entity l'entité à mettre à jour
     */
    void update(T entity);

    /**
     * Supprime une entité de la base de données.
     *
     * @param entity l'entité à supprimer
     */
    void delete(T entity);

    /**
     * Recherche une entité par son identifiant.
     *
     * @param id l'identifiant de l'entité
     * @return un Optional contenant l'entité si trouvée, sinon vide
     */
    Optional<T> findById(ID id);

    /**
     * Récupère toutes les entités.
     *
     * @return une liste de toutes les entités
     */
    List<T> findAll();

    /**
     * Recherche une entité par son adresse e-mail.
     *
     * @param email l'adresse e-mail de l'entité
     * @return un Optional contenant l'entité si trouvée, sinon vide
     */
    Optional<T> findByEmail(String email);

    /**
     * Recherche des entités par un critère de recherche.
     *
     * @param query le critère de recherche
     * @return une liste d'entités correspondant au critère
     */
    List<T> search(String query);
}