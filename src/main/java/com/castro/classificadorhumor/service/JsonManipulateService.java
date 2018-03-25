package com.castro.classificadorhumor.service;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface JsonManipulateService {

    List<Ticket> list() throws IOException;
}
