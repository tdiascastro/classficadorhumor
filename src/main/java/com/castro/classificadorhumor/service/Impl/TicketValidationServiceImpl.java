package com.castro.classificadorhumor.service.Impl;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.repository.JsonManipulateRepository;
import com.castro.classificadorhumor.service.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TicketValidationServiceImpl implements TicketValidationService {

    private static final String ALTA = "Alta";
    private static final String NORMAL = "Normal";

    final private Pattern REGEX_WORDS = Pattern.compile("(reclamacao+)|(nao recebemos+)|(nao consigo acessar+)|(tentativa de contato+)|(cancelamento+)|(quero cancelar+)|(problema+)|(procon+)|(reclameaqui+)|(reclame+)");

    private JsonManipulateRepository service;

    @Autowired
    public TicketValidationServiceImpl(final JsonManipulateRepository service) {
        this.service = service;
    }

    @Override
    public List<Ticket> priorizedTickets(LocalDate startDate, LocalDate endDate, String priority) throws IOException {
        return service.updateJson(priorityAnalyse(service.jsonRead()))
                .stream()
                .filter(t -> startDateFilter(t, Optional.ofNullable(startDate)))
                .filter(t -> endDateFilter(t, Optional.ofNullable(endDate)))
                .filter(t -> priorityFilter(t, Optional.ofNullable(priority)))
                .sorted(Comparator.comparing(Ticket::getDateCreate, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Comparator.comparing(Ticket::getDateUpdate, Comparator.nullsLast(Comparator.naturalOrder())))
                        .thenComparing(Comparator.comparing(Ticket::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))))
                .collect(Collectors.toList());
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

    private static boolean startDateFilter(final Ticket ticket, final Optional<LocalDate> startDate) {
        return !startDate.isPresent() ||
                ticket.getDateCreate().isAfter(LocalDateTime.of(startDate.get(), LocalTime.of(0,0,0)));
    }

    private static boolean endDateFilter(final Ticket ticket, final Optional<LocalDate> endDate) {
        return !endDate.isPresent() ||
                ticket.getDateCreate().isBefore(LocalDateTime.of(endDate.get(), LocalTime.of(23,59,59)));
    }

    private static boolean priorityFilter(final Ticket ticket, final Optional<String> priority) {
        return !priority.isPresent() ||
                ticket.getPriority().equals(priority.get());
    }

}
