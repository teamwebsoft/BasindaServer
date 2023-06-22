package com.basinda.controllers;

import com.basinda.entities.District;
import com.basinda.entities.Division;
import com.basinda.entities.Pourosova;
import com.basinda.requests.DistrictRequest;
import com.basinda.requests.DivisionRequest;
import com.basinda.requests.PourosovaRequest;
import com.basinda.responses.ResponseHeader;
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

    public class Response extends ResponseHeader{

    }

    @PostMapping("/admin/create/division")
    public ResponseEntity<Response> createDivision(@RequestBody DivisionRequest model){
        Response response = new Response();
        String createdDivision = locationService.createDivision(model);
        if (createdDivision.equalsIgnoreCase("created")){
            response.setStatusCode(HttpStatus.CREATED);
            response.setStatus(true);
            response.setContent("Division created successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/admin/create/district")
    public ResponseEntity<Response> createDistrict(@RequestBody DistrictRequest model){
        Response response = new Response();
        String createdDistrict = locationService.createDistrict(model);
        if (createdDistrict.equalsIgnoreCase("created")){
            response.setStatusCode(HttpStatus.CREATED);
            response.setStatus(true);
            response.setContent("District created successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/admin/create/pourosova")
    public ResponseEntity<Response> createPourosova(@RequestBody PourosovaRequest model){
        Response response = new Response();
        String createdPourosova = locationService.createPourosova(model);
        if (createdPourosova.equalsIgnoreCase("created")){
            response.setStatusCode(HttpStatus.CREATED);
            response.setStatus(true);
            response.setContent("Pourosova created successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
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