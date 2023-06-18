package com.basinda.services.impl;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;
import com.basinda.services.LocationService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.DistrictRepository;
import com.basinda.repositories.DivisionRepository;
import com.basinda.repositories.PourosovaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PourosovaRepository pourosovaRepository;

    @Override
    public Division createDivision(Division division) {
        return divisionRepository.save(division);
    }

    @Override
    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public Pourosova createPourosova(Pourosova pourosova) {
        return pourosovaRepository.save(pourosova);
    }

    @Override
    public List<Division> readDivision() {
        return divisionRepository.findAll();
    }

    @Override
    public List<District> readDistrictForDivision(Long divisionId) {
        return districtRepository.findByDivisionId(divisionId);
    }

    @Override
    public List<Pourosova> readPourosovaForDistrict(Long districtId) {
        return pourosovaRepository.findByDistrictId(districtId);
    }
}