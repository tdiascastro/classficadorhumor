package com.castro.classificadorhumor.service.impl;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.service.JsonManipulateService;
import com.castro.classificadorhumor.service.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TicketValidationImpl implements TicketValidationService {

    final private Pattern REGEX_WORDS = Pattern.compile("(reclamacao+)|(nao recebemos+)|(nao consigo acessar+)|(tentativa de contato+)|(cancelamento+)|(quero cancelar+)|(problema+)|(procon+)|(reclameaqui+)|(reclame+)");

    private JsonManipulateService service;

    @Autowired
    public TicketValidationImpl(final JsonManipulateService service) {
        this.service = service;
    }

    @Override
    public List<Ticket> highPriority() throws IOException {
        return priorityAnalyse(service.list());
    }

    private List<Ticket> priorityAnalyse(final List<Ticket> tickets) {
        final List<Ticket> highPriorityTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            final int interaction = ticket.getInteractions().size();
            for (int i = 0; i <= interaction; i++) {
                final Matcher matcher = REGEX_WORDS.matcher(ticket.getInteractions().get(i).getMessage());
                if (matcher.find()){
                    highPriorityTickets.add(ticket);
                    break;
                }
                final long days = ChronoUnit.DAYS.between(ticket.getDateCreate(), ticket.getDateUpdate());
                if (days > 30) {
                    highPriorityTickets.add(ticket);
                    break;
                }

            }
        }
        return highPriorityTickets;
    }
}
