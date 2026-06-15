package io.valkycodes.trs.controller;

import io.valkycodes.trs.context.AuthenticatedUserContext;
import io.valkycodes.trs.model.po.Ticket;
import io.valkycodes.trs.model.po.Train;
import io.valkycodes.trs.service.TicketService;
import io.valkycodes.trs.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private AuthenticatedUserContext authenticatedUserContext;

    @GetMapping("/book/{trainNumber}")
    public String bookTicketForm(@PathVariable Integer trainNumber, Model model) {
        model.addAttribute("trainNumber", trainNumber);
        return "book";
    }

    @PostMapping("/book")
    public String bookTicket(@RequestParam Integer trainNumber,
                             @RequestParam Integer numberOfSeats,
                             @RequestParam String travelClass) {
        String username = authenticatedUserContext.getUserData().getUsername();
        Ticket ticket = ticketService.bookTicket(username, trainNumber, numberOfSeats, travelClass);
        if (ticket != null) {
            return "redirect:/home";
        }
        return "redirect:/book/" + trainNumber + "?error=true";
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(required = false) String pnr, Model model) {
        String username = authenticatedUserContext.getUserData().getUsername();
        
        if (pnr != null && !pnr.isEmpty()) {
            Optional<Ticket> ticketOpt = ticketService.getTicketByPnrAndUsername(pnr, username);
            if (ticketOpt.isPresent()) {
                model.addAttribute("tickets", List.of(ticketOpt.get()));
            } else {
                model.addAttribute("searchError", "No ticket found with this PNR.");
            }
            model.addAttribute("searchPnr", pnr);
        } else {
            List<Ticket> tickets = ticketService.getUserTickets(username);
            model.addAttribute("tickets", tickets);
        }
        return "dashboard";
    }

    @GetMapping("/ticket/{pnr}")
    public String viewTicket(@PathVariable String pnr, Model model) {
        String username = authenticatedUserContext.getUserData().getUsername();
        Optional<Ticket> ticketOpt = ticketService.getTicketByPnrAndUsername(pnr, username);
        
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            model.addAttribute("ticket", ticket);
            Optional<Train> trainOpt = trainService.getTrainByNumber(ticket.getTrainNumber());
            trainOpt.ifPresent(train -> model.addAttribute("train", train));
            return "ticket_view";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/ticket/{pnr}/cancel")
    public String cancelTicket(@PathVariable String pnr) {
        String username = authenticatedUserContext.getUserData().getUsername();
        ticketService.cancelTicket(pnr, username);
        return "redirect:/dashboard";
    }
}
