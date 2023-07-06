package com.basinda.services;

import com.basinda.models.entity.District;
import com.basinda.models.entity.Division;
import com.basinda.models.entity.Pourosova;
import com.basinda.models.request.admin.DistrictRequest;
import com.basinda.models.request.admin.DivisionRequest;
import com.basinda.models.request.admin.PourosovaRequest;

import java.util.List;

public interface LocationService {
    String createDivision(DivisionRequest request);
    String createDistrict(DistrictRequest request);
    String createPourosova(PourosovaRequest request);
    List<Division> readDivision();
    List<District> readDistrictForDivision(Long divisionId);
    List<Pourosova> readPourosovaForDistrict(Long districtId);
}