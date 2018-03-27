package com.castro.classificadorhumor.repository.impl;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.repository.JsonManipulateService;
import com.castro.classificadorhumor.repository.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.Normalizer;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TicketValidationImpl implements TicketValidationService {

    private static final String ALTA = "Alta";
    private static final String NORMAL = "Normal";

    final private Pattern REGEX_WORDS = Pattern.compile("(reclamacao+)|(nao recebemos+)|(nao consigo acessar+)|(tentativa de contato+)|(cancelamento+)|(quero cancelar+)|(problema+)|(procon+)|(reclameaqui+)|(reclame+)");

    private JsonManipulateService service;

    @Autowired
    public TicketValidationImpl(final JsonManipulateService service) {
        this.service = service;
    }

    @Override
    public List<Ticket> priorizedTickets() throws IOException {
        return service.updateJson(priorityAnalyse(service.jsonRead()));
    }

    private List<Ticket> priorityAnalyse(final List<Ticket> tickets) {
        final List<Ticket> priorizedTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            setPriority(ticket, NORMAL);
            final long days = ChronoUnit.DAYS.between(ticket.getDateCreate(), ticket.getDateUpdate());
            if (days > 30) {
                setPriority(ticket, ALTA);
            }
            final int interaction = ticket.getInteractions().size();
            for (int i = 0; i < interaction; i++) {
                final Matcher matcher = REGEX_WORDS.matcher(Normalizer.normalize(ticket.getInteractions().get(i).getMessage().toLowerCase(),
                        Normalizer.Form.NFD).toLowerCase().replaceAll("[^\\p{ASCII}]", ""));
                if (matcher.find()) {
                    setPriority(ticket, ALTA);
                }
            }
            priorizedTickets.add(ticket);
        }
        return priorizedTickets;
    }

    private void setPriority(final Ticket ticket, final String priority) {
        ticket.setPriority(priority);
    }
}
