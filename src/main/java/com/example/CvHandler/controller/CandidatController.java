package com.example.CvHandler.controller;

import com.example.CvHandler.controller.Exception.NotFoundException;
import com.example.CvHandler.dto.CandidatDTO;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.model.CurriculumVitae;
import com.example.CvHandler.service.CandidatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/candidats")
    public ResponseEntity<?> getCandidats(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(name = "withDetails", defaultValue = "false") boolean withDetails) {
        Pageable pageable = PageRequest.of(page, size);
        Object response =  null;

        if (withDetails) {
            Iterable<Candidat> candidats = candidatService.getAllCandidats(pageable);

            List<CandidatDTO> dtos = new ArrayList<>();
            for (Candidat candidat : candidats) {
                CandidatDTO candidatDTO = modelMapper.map(candidat, CandidatDTO.class);
                dtos.add(candidatDTO);

            }
            response = dtos;
        } else {
            response = candidatService.getAllCandidatWithoutDetails(pageable);
        }
        int totalPages = (int) Math.ceil(candidatService.countAllCandidats() / (double) size);
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Total-Elements", size.toString());

        headers.add("X-Total-Pages", Integer.toString(totalPages));
        return ResponseEntity.ok().headers(headers).body(response);

    }


    @PutMapping("/candidats/{id}")
    public ResponseEntity<?> updateCandidat(@PathVariable Long id, @RequestBody Candidat updatedCandidat) {
        Candidat updatedCandidatResponse = candidatService.updateCandidatAttributes(id, updatedCandidat);

        if (updatedCandidatResponse != null) {
            CandidatDTO candidatDTO = modelMapper.map(updatedCandidatResponse, CandidatDTO.class);
            return ResponseEntity.ok(candidatDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/candidats/{id}")
    public ResponseEntity<?> deleteCandidat(@PathVariable Long id) {
        candidatService.deleteCandidat(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search/nom")
    public ResponseEntity<?> searchByNom(@RequestParam(name = "query") String query) {
        if (query.length() < 3) {
            return ResponseEntity.badRequest().body("Le terme de recherche doit avoir au moins trois lettres.");
        }
        List<Candidat> candidats = candidatService.searchByNom(query);
        return ResponseEntity.ok(candidats);
    }

    @GetMapping("/search/prenom")
    public ResponseEntity<?> searchByPrenom(@RequestParam(name = "query") String query) {
        if (query.length() < 3) {
            return ResponseEntity.badRequest().body("Le terme de recherche doit avoir au moins trois lettres.");
        }
        List<Candidat> candidats = candidatService.searchByPrenom(query);
        return ResponseEntity.ok(candidats);
    }

    // Vous pouvez également ajouter une méthode pour rechercher par les deux (nom et prénom) si nécessaire
    @GetMapping("/search/both")
    public ResponseEntity<?> searchByNomAndPrenom(
            @RequestParam(name = "nom") String nom,
            @RequestParam(name = "prenom") String prenom) {
        List<Candidat> candidats = candidatService.searchByNomAndPrenom(nom, prenom);
        return ResponseEntity.ok(candidats);
    }


    @GetMapping("/{id}/cv")
    public ResponseEntity<CurriculumVitae> getCV(@PathVariable Long id) {
        Optional<Candidat> candidat = candidatService.getCandidatById(id);
        if (candidat.isPresent()) {
            CurriculumVitae cv = candidat.get().getCv();
            if (cv != null) {
                return new ResponseEntity<>(cv, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }






}




