package com.basinda.services;

import com.basinda.entities.Flat;

import java.util.List;

public interface FlatService {
    List<Flat> read();
    Flat createFlat(Flat flat);
}