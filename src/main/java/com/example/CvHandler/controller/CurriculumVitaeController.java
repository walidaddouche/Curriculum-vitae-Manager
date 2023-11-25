package com.example.CvHandler.controller;

import com.example.CvHandler.model.CurriculumVitae;
import com.example.CvHandler.service.CurriculumVitaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cvs")
public class CurriculumVitaeController {

    private final CurriculumVitaeService curriculumVitaeService;

    @Autowired
    public CurriculumVitaeController(CurriculumVitaeService curriculumVitaeService) {
        this.curriculumVitaeService = curriculumVitaeService;
    }

    @PostMapping
    public ResponseEntity<CurriculumVitae> saveCV(@RequestBody CurriculumVitae cv) {
        CurriculumVitae savedCV = curriculumVitaeService.saveCV(cv);
        return new ResponseEntity<>(savedCV, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumVitae> getCVById(@PathVariable Long id) {
        Optional<CurriculumVitae> cv = curriculumVitaeService.getCVById(id);
        return cv.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<Iterable<CurriculumVitae>> getAllCVs() {
        Iterable<CurriculumVitae> cvs = curriculumVitaeService.getAllCVs();
        return new ResponseEntity<>(cvs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCV(@PathVariable Long id) {
        curriculumVitaeService.deleteCV(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
