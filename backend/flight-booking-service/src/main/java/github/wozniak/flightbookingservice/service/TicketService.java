package github.wozniak.flightbookingservice.service;

import github.wozniak.flightbookingservice.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : Jackson Wozniak
 * @created : 8/30/2023, Wednesday
 **/
@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
}
