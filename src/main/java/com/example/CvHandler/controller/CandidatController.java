package com.example.CvHandler.controller;

import com.example.CvHandler.controller.Exception.NotFoundException;
import com.example.CvHandler.dto.CandidatDTO;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.service.CandidatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/candidat")
public class CandidatController {
    private final CandidatService candidatService;
    private final ModelMapper modelMapper;


    @Autowired
    CandidatController(CandidatService candidatService,ModelMapper modelMapper) {
        this.candidatService = candidatService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidatById(@PathVariable Long id) {
        Optional<Candidat> candidatOptional = candidatService.getCandidatById(id);
        if (candidatOptional.isEmpty()) {
            throw new NotFoundException("Candidat Introuvable ",HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(modelMapper.map(candidatOptional.get(), CandidatDTO.class));
    }





}



