package jobOffers.service;


import employes.Employee;
import jakarta.persistence.EntityNotFoundException;
import jobOffers.JobOffer;
import jobOffers.dao.JobOfferDAO;

import java.util.List;

import java.util.UUID;

public class JobOfferServiceImpl implements JobOfferService{

    private JobOfferDAO jobOfferDao;

    public JobOfferServiceImpl(JobOfferDAO jobOfferDao) {
        this.jobOfferDao = jobOfferDao;
    }

    @Override
    public void save(JobOffer jobOffer) {
        jobOfferDao.save(jobOffer);
    }

    @Override
    public List<JobOffer> getAll() {
        return jobOfferDao.getAll();
    }

    @Override
    public JobOffer get(UUID id) {
        return jobOfferDao.get(id)
                .orElseThrow(() -> new EntityNotFoundException("JobOffer not found with id: " + id));
    }

    @Override
    public void update(JobOffer jobOffer) {
        jobOfferDao.update(jobOffer);
    }

    @Override
    public void delete(UUID id) {
        jobOfferDao.delete(id);
    }


}
