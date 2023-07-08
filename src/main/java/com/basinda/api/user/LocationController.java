package com.basinda.api.user;

import com.basinda.models.entity.District;
import com.basinda.models.entity.Division;
import com.basinda.models.entity.Pourosova;
import com.basinda.models.entity.Upozila;
import com.basinda.models.response.ResponseHeader;
import com.basinda.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    public class Response extends ResponseHeader{

    }

    @GetMapping("/divisions")
    public ResponseEntity<List<Division>> getAllDivisions(){
        List<Division> divisions = locationService.readDivision();
        return ResponseEntity.ok(divisions);
    }

    @GetMapping("/districts/{divisionId}")
    public ResponseEntity<List<District>> getAllDistrictsForDivision(@PathVariable Long divisionId){
        List<District> districts = locationService.readDistrictForDivision(divisionId);
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/upozilas/{districtId}")
    public ResponseEntity<List<Upozila>> getAllUpozilasForDistrict(@PathVariable Long districtId){
        List<Upozila> upozila = locationService.readUpozilaForDistrict(districtId);
        return ResponseEntity.ok(upozila);
    }

    @GetMapping("/pourosovas/{upozilaId}")
    public ResponseEntity<List<Pourosova>> getAllPourosovasForDistrict(@PathVariable Long upozilaId){
        List<Pourosova> pourosovas = locationService.readPourosovaForUpozila(upozilaId);
        return ResponseEntity.ok(pourosovas);
    }
}