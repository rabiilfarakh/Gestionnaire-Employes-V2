import jobOffers.JobOffer;
import jobOffers.dao.JobOfferDAO;
import jobOffers.service.JobOfferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobOfferServiceImplTest {

    @Mock
    private JobOfferDAO jobOfferDAO;

    @InjectMocks
    private JobOfferServiceImpl jobOfferService;

    private JobOffer jobOffer;

    @BeforeEach
    void setUp() {
        jobOffer = new JobOffer("Developer", "Remote", "Develop awesome applications", 60000, null);
    }

    @Test
    void testSave() {
        doNothing().when(jobOfferDAO).save(any(JobOffer.class));

        jobOfferService.save(jobOffer);

        verify(jobOfferDAO, times(1)).save(jobOffer);
    }

    @Test
    void testGetAll() {
        List<JobOffer> jobOffers = Arrays.asList(jobOffer);
        when(jobOfferDAO.getAll()).thenReturn(jobOffers);

        List<JobOffer> result = jobOfferService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(jobOffer.getTitle(), result.get(0).getTitle());
    }

    @Test
    void testGet() {
        UUID id = UUID.randomUUID();
        when(jobOfferDAO.get(id)).thenReturn(Optional.of(jobOffer));

        JobOffer result = jobOfferService.get(id);

        assertNotNull(result);
        assertEquals(jobOffer.getTitle(), result.getTitle());
    }

    @Test
    void testGet_NotFound() {
        UUID id = UUID.randomUUID();
        when(jobOfferDAO.get(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            jobOfferService.get(id);
        });

        assertEquals("JobOffer not found with id: " + id, exception.getMessage());
    }

    @Test
    void testUpdate() {
        doNothing().when(jobOfferDAO).update(any(JobOffer.class));

        jobOfferService.update(jobOffer);

        verify(jobOfferDAO, times(1)).update(jobOffer);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        doNothing().when(jobOfferDAO).delete(id);

        jobOfferService.delete(id);

        verify(jobOfferDAO, times(1)).delete(id);
    }
}
