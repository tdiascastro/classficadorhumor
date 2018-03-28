package com.castro.classificadorhumor.service;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface JsonManipulateService {

    List<Ticket> jsonRead() throws IOException;

    List<Ticket> updateJson(List<Ticket> tickets) throws IOException;
}