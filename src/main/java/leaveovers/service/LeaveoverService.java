package leaveovers.service;

import leaveovers.Leaveover;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveoverService {

    void save(Leaveover leaveover);


    void update(Leaveover leaveover);


    void delete(Leaveover leaveover);


    Optional<Leaveover> findById(UUID id);


    List<Leaveover> findAll();

}
