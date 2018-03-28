package com.castro.classificadorhumor.repository;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface JsonManipulateRepository {

    List<Ticket> jsonRead() throws IOException;

    List<Ticket> updateJson(List<Ticket> tickets) throws IOException;
}
