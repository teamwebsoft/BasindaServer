package com.basinda.services.impl;

import com.basinda.models.entity.District;
import com.basinda.models.entity.Division;
import com.basinda.models.entity.Pourosova;
import com.basinda.models.entity.Upozila;
import com.basinda.models.request.admin.DistrictRequest;
import com.basinda.models.request.admin.DivisionRequest;
import com.basinda.models.request.admin.PourosovaRequest;
import com.basinda.models.request.admin.UpozilaRequest;
import com.basinda.repositories.UpozilaRepository;
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
    private UpozilaRepository upozilaRepository;

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
    public String createUpozila(UpozilaRequest request) {
        Optional<District> district = districtRepository.findById(request.getDistrictId());
        if (district != null) {
            Upozila upozila = new Upozila();
            upozila.setName(request.getName());
            upozila.setDistrictId(request.getDistrictId());
            Upozila createUpozila = upozilaRepository.save(upozila);
            if (createUpozila != null) {
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
        Optional<Upozila> upozila = upozilaRepository.findById(request.getUpozilaId());
        if (upozila != null) {
            Pourosova pourosova = new Pourosova();
            pourosova.setName(request.getName());
            pourosova.setUpozilaId(request.getUpozilaId());
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
    public List<Upozila> readUpozilaForDistrict(Long districtId) {
        return upozilaRepository.findByDistrictId(districtId);
    }

    @Override
    public List<Pourosova> readPourosovaForUpozila(Long upozilaId) {
        return pourosovaRepository.findByUpozilaId(upozilaId);
    }
}