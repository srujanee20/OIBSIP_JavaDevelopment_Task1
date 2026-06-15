package io.valkycodes.trs.repository;

import io.valkycodes.trs.model.po.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    List<Train> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
}
