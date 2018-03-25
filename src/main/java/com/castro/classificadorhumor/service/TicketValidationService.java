package com.castro.classificadorhumor.service;

import com.castro.classificadorhumor.models.Ticket;

import java.util.List;

public interface TicketValidationService {

    List<Ticket> highPriority();

    List<Ticket> priorityAnalyse();
}
