package leaveovers.service;

import leaveovers.Leaveover;
import leaveovers.LeaveoverRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeaveoverServiceImpl implements LeaveoverService {

    private final LeaveoverRepository leaveoverRepository;

    public LeaveoverServiceImpl(LeaveoverRepository leaveoverRepository) {
        this.leaveoverRepository = leaveoverRepository;
    }

    @Override
    public void save(Leaveover leaveover) {
        leaveoverRepository.save(leaveover);
    }

    @Override
    public void update(Leaveover leaveover) {
        leaveoverRepository.update(leaveover);
    }

    @Override
    public void delete(Leaveover leaveover) {
        leaveoverRepository.delete(leaveover);
    }

    @Override
    public Optional<Leaveover> findById(UUID id) {
        return leaveoverRepository.findById(id);
    }

    @Override
    public List<Leaveover> findAll() {
        return leaveoverRepository.findAll();
    }
}
