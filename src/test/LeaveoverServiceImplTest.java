
import leaveovers.Leaveover;
import leaveovers.LeaveoverRepository;
import leaveovers.service.LeaveoverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaveoverServiceImplTest {

    @Mock
    private LeaveoverRepository leaveoverRepository;

    @InjectMocks
    private LeaveoverServiceImpl leaveoverService;

    private Leaveover leaveover;
    private UUID leaveoverId;

    @BeforeEach
    void setUp() {
        leaveoverId = UUID.randomUUID();
        leaveover = new Leaveover();
        leaveover.setId(leaveoverId);
    }

    @Test
    void testSave() {
        doNothing().when(leaveoverRepository).save(any(Leaveover.class));
        leaveoverService.save(leaveover);
        verify(leaveoverRepository, times(1)).save(leaveover);
    }

    @Test
    void testUpdate() {
        doNothing().when(leaveoverRepository).update(any(Leaveover.class));
        leaveoverService.update(leaveover);
        verify(leaveoverRepository, times(1)).update(leaveover);
    }

    @Test
    void testDelete() {
        doNothing().when(leaveoverRepository).delete(any(Leaveover.class));
        leaveoverService.delete(leaveover);
        verify(leaveoverRepository, times(1)).delete(leaveover);
    }

    @Test
    void testFindById() {
        when(leaveoverRepository.findById(leaveoverId)).thenReturn(Optional.of(leaveover));
        Optional<Leaveover> foundLeaveover = leaveoverService.findById(leaveoverId);
        assertTrue(foundLeaveover.isPresent());
        assertEquals(leaveover, foundLeaveover.get());
    }

    @Test
    void testFindAll() {
        List<Leaveover> mockLeaveovers = Arrays.asList(leaveover);
        when(leaveoverRepository.findAll()).thenReturn(mockLeaveovers);

        List<Leaveover> leaveovers = leaveoverService.findAll();

        assertNotNull(leaveovers);
        assertEquals(1, leaveovers.size());
        assertEquals(leaveover, leaveovers.get(0));
    }
}
