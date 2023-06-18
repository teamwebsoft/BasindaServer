package com.basinda.services.impl;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;
import com.basinda.requests.DistrictRequest;
import com.basinda.requests.DivisionRequest;
import com.basinda.requests.PourosovaRequest;
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
    public Division createDivision(DivisionRequest request) {
        Division division = new Division();
        division.setName(request.getName());
        return divisionRepository.save(division);
    }

    @Override
    public District createDistrict(DistrictRequest request) {
        District district = new District();
        district.setName(request.getName());
        district.setDivisionId(request.getDivisionId());
        return districtRepository.save(district);
    }

    @Override
    public Pourosova createPourosova(PourosovaRequest request) {
        Pourosova pourosova = new Pourosova();
        pourosova.setName(request.getName());
        pourosova.setDistrictId(request.getDistrictId());
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