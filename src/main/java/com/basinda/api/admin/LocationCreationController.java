package com.basinda.api.admin;

import com.basinda.models.request.admin.UpozilaRequest;
import org.springframework.http.HttpStatus;
import com.basinda.models.request.admin.DistrictRequest;
import com.basinda.models.request.admin.DivisionRequest;
import com.basinda.models.response.ResponseHeader;
import com.basinda.services.LocationService;
import com.basinda.models.request.admin.PourosovaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/admin/create")
public class LocationCreationController {

    @Autowired
    private LocationService locationService;

    public class Response extends ResponseHeader {

    }

    @PostMapping("/division")
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

    @PostMapping("/district")
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

    @PostMapping("/upozila")
    public ResponseEntity<Response> createUpozila(@RequestBody UpozilaRequest model){
        Response response = new Response();
        String createdUpozila = locationService.createUpozila(model);
        if (createdUpozila.equalsIgnoreCase("created")){
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

    @PostMapping("/pourosova")
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
}