package com.castro.classificadorhumor.repository;

import com.castro.classificadorhumor.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface JsonManipulateService {

    List<Ticket> jsonRead() throws IOException;
}
