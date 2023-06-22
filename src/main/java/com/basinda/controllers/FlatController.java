package com.basinda.controllers;

import com.basinda.entities.Flat;
import com.basinda.responses.ResponseHeader;
import com.basinda.services.FlatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/flats")
public class FlatController {

    @Autowired
    private FlatService flatService;

    public class Response extends ResponseHeader{

    }

    @GetMapping("/all")
    public ResponseEntity<List<Flat>> getAllFlats(){
        return ResponseEntity.ok(flatService.read());
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createFlat(@RequestBody Flat model){

        Response response = new Response();
        Flat flat = flatService.createFlat(model);
        if (flat == null){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        else{
            response.setStatusCode(HttpStatus.CREATED);
            response.setStatus(true);
            response.setContent("Flat created successfully.");
        }

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}