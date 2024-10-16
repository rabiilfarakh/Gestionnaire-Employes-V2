package jobOffers.service;

import jobOffers.JobOffer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobOfferService {

    void save(JobOffer employee);

    List<JobOffer> getAll();

    JobOffer get(UUID id);

    void update(JobOffer employee);

    void delete(UUID id);

}
