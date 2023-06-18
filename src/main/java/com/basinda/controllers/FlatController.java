package com.basinda.controllers;

import com.basinda.entities.Flat;
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

    @GetMapping("/all")
    public ResponseEntity<List<Flat>> getAllFlats(){
        return ResponseEntity.ok(flatService.read());
    }

    @PostMapping("/create")
    public ResponseEntity<Flat> createFlat(@RequestBody Flat model){
        Flat flat = flatService.createFlat(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(flat);
    }
}