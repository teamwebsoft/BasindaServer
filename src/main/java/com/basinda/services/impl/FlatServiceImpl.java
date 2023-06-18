package com.basinda.services.impl;

import com.basinda.entities.Flat;
import com.basinda.services.FlatService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service
public class FlatServiceImpl implements FlatService {

    @Autowired
    private FlatRepository flatRepository;

    @Override
    public List<Flat> read() {
        return flatRepository.findAll();
    }

    @Override
    public Flat createFlat(Flat flat) {
        return flatRepository.save(flat);
    }
}