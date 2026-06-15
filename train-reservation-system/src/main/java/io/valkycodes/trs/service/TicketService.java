package io.valkycodes.trs.service;

import io.valkycodes.trs.model.po.Ticket;
import io.valkycodes.trs.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TrainService trainService;

    public Ticket bookTicket(String username, Integer trainNumber, Integer numberOfSeats, String travelClass) {
        if (trainService.deductSeats(trainNumber, numberOfSeats)) {
            Ticket ticket = new Ticket();
            ticket.setUsername(username);
            ticket.setTrainNumber(trainNumber);
            ticket.setNumberOfSeats(numberOfSeats);
            ticket.setTravelClass(travelClass);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public List<Ticket> getUserTickets(String username) {
        return ticketRepository.findByUsername(username);
    }

    public Optional<Ticket> getTicketByPnrAndUsername(String pnr, String username) {
        return ticketRepository.findByPnrAndUsername(pnr, username);
    }

    public Optional<Ticket> getTicketByPnr(String pnr) {
        return ticketRepository.findById(pnr);
    }

    public boolean cancelTicket(String pnr, String username) {
        Optional<Ticket> optionalTicket = ticketRepository.findByPnrAndUsername(pnr, username);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            trainService.releaseSeats(ticket.getTrainNumber(), ticket.getNumberOfSeats());
            ticketRepository.delete(ticket);
            return true;
        }
        return false;
    }
}
