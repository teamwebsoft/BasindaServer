package com.basinda.services.impl;

import com.basinda.models.entity.Flat;
import com.basinda.services.FlatService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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