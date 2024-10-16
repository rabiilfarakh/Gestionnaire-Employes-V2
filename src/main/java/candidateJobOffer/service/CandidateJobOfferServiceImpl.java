package candidateJobOffer.service;

import candidateJobOffer.CandidateJobOffer;
import candidateJobOffer.dao.CandidateJobOfferDAO;


public class CandidateJobOfferServiceImpl implements CandidateJobOfferService{

    private CandidateJobOfferDAO candidateJobOfferDao;

    public CandidateJobOfferServiceImpl(CandidateJobOfferDAO candidateJobOfferDao) {
        this.candidateJobOfferDao = candidateJobOfferDao;
    }

    @Override
    public void save(CandidateJobOffer candidateJobOffer) {

    }
}
