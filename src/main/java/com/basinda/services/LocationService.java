package com.basinda.services;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;

import java.util.List;

public interface LocationService {
    Division createDivision(Division division);
    District createDistrict(District district);
    Pourosova createPourosova(Pourosova pourosova);
    List<Division> readDivision();
    List<District> readDistrictForDivision(Long divisionId);
    List<Pourosova> readPourosovaForDistrict(Long districtId);
}