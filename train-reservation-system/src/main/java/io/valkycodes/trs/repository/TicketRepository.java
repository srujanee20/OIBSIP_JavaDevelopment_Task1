package io.valkycodes.trs.repository;

import io.valkycodes.trs.model.po.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByUsername(String username);
    Optional<Ticket> findByPnrAndUsername(String pnr, String username);
}
