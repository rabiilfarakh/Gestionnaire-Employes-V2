package jobOffers.dao;

import jobOffers.JobOffer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobOfferDAO {

    void save(JobOffer employee);

    List<JobOffer> getAll();

    Optional<JobOffer> get(UUID id);

    void update(JobOffer employee);

    void delete(UUID id);

}
