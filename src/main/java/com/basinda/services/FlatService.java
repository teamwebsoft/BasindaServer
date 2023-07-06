package com.basinda.services;

import com.basinda.models.entity.Flat;

import java.util.List;

public interface FlatService {
    List<Flat> read();
    Flat createFlat(Flat flat);
}