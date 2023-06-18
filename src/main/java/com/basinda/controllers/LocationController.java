package com.basinda.controllers;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;
import com.basinda.requests.DistrictRequest;
import com.basinda.requests.DivisionRequest;
import com.basinda.requests.PourosovaRequest;
import org.springframework.http.HttpStatus;
import com.basinda.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/admin/create/division")
    public ResponseEntity<Division> createDivision(@RequestBody DivisionRequest model){
        Division division = locationService.createDivision(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(division);
    }

    @PostMapping("/admin/create/district")
    public ResponseEntity<District> createDistrict(@RequestBody DistrictRequest model){
        District district = locationService.createDistrict(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(district);
    }

    @PostMapping("/admin/create/pourosova")
    public ResponseEntity<Pourosova> createPourosova(@RequestBody PourosovaRequest model){
        Pourosova pourosova = locationService.createPourosova(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(pourosova);
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

    @GetMapping("/pourosovas/{districtId}")
    public ResponseEntity<List<Pourosova>> getAllPourosovasForDistrict(@PathVariable Long districtId){
        List<Pourosova> pourosovas = locationService.readPourosovaForDistrict(districtId);
        return ResponseEntity.ok(pourosovas);
    }
}