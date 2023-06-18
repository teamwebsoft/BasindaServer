package com.basinda.services;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;
import com.basinda.requests.DistrictRequest;
import com.basinda.requests.DivisionRequest;
import com.basinda.requests.PourosovaRequest;

import java.util.List;

public interface LocationService {
    Division createDivision(DivisionRequest request);
    District createDistrict(DistrictRequest request);
    Pourosova createPourosova(PourosovaRequest request);
    List<Division> readDivision();
    List<District> readDistrictForDivision(Long divisionId);
    List<Pourosova> readPourosovaForDistrict(Long districtId);
}