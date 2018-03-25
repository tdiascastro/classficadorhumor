package com.castro.classificadorhumor.service.impl;

import com.castro.classificadorhumor.models.Ticket;
import com.castro.classificadorhumor.service.JsonManipulateService;
import com.castro.classificadorhumor.service.TicketValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketValidationImpl implements TicketValidationService {

    private JsonManipulateService service;

    @Autowired
    public TicketValidationImpl(final JsonManipulateService service) {
        this.service = service;
    }

    @Override
    public List<Ticket> highPriority() {

        return null;
    }

    public List<Ticket> priorityAnalyse(){
        return null;
    }
}
