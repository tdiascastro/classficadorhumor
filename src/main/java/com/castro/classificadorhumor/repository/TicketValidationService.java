package com.castro.classificadorhumor.repository;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface TicketValidationService {

    List<Ticket> priorizedTickets() throws IOException;
}
