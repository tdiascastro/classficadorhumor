package com.castro.classificadorhumor.service;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface TicketValidationService {

    List<Ticket> highPriority() throws IOException;
}
