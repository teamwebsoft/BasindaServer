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
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PourosovaRepository pourosovaRepository;

    @Override
    public String createDivision(DivisionRequest request) {
        Division division = new Division();
        division.setName(request.getName());
        Division createdDivision = divisionRepository.save(division);
        if (createdDivision != null) {
            return "Created";
        }
        else{
            return "Not Created";
        }
    }

    @Override
    public String createDistrict(DistrictRequest request) {
        Optional<Division> division = divisionRepository.findById(request.getDivisionId());
        if (division != null) {
            District district = new District();
            district.setName(request.getName());
            district.setDivisionId(request.getDivisionId());
            District createdDistrict = districtRepository.save(district);
            if (createdDistrict != null) {
                return "Created";
            }
            else{
                return "Not Created";
            }
        }
        else{
            return "Not Created";
        }

    }

    @Override
    public String createPourosova(PourosovaRequest request) {
        Optional<District> district = districtRepository.findById(request.getDistrictId());
        if (district != null) {
            Pourosova pourosova = new Pourosova();
            pourosova.setName(request.getName());
            pourosova.setDistrictId(request.getDistrictId());
            Pourosova createdPourosova = pourosovaRepository.save(pourosova);
            if (createdPourosova != null) {
                return "Created";
            }
            else{
                return "Not Created";
            }
        }
        else{
            return "Not Created";
        }
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