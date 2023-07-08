package com.basinda.services.impl;

import com.basinda.models.entity.Profession;
import com.basinda.models.request.ProfessionRequest;
import com.basinda.repositories.ProfessionRepository;
import com.basinda.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Override
    public List<Profession> getAllProfession() {
        return professionRepository.findAll();
    }

    @Override
    public Profession createProfession(ProfessionRequest request) {
        Profession profession = new Profession();
        profession.setName(request.getName());

        Profession createdProfession = professionRepository.save(profession);
        return createdProfession;
    }
}