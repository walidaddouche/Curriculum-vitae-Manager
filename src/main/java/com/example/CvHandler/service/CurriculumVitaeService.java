package com.example.CvHandler.service;

import com.example.CvHandler.model.CurriculumVitae;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CvHandler.repository.CurriculumVitaeRepository;

import java.util.Optional;

@Service
public class CurriculumVitaeService {

    private final CurriculumVitaeRepository curriculumVitaeRepository;

    @Autowired
    public CurriculumVitaeService(CurriculumVitaeRepository curriculumVitaeRepository) {
        this.curriculumVitaeRepository = curriculumVitaeRepository;
    }

    public CurriculumVitae saveCV(CurriculumVitae cv) {
        return curriculumVitaeRepository.save(cv);
    }

    public Optional<CurriculumVitae> getCVById(Long id) {
        return curriculumVitaeRepository.findById(id);
    }

    public Iterable<CurriculumVitae> getAllCVs() {
        return curriculumVitaeRepository.findAll();
    }

    public void deleteCV(Long id) {
        curriculumVitaeRepository.deleteById(id);
    }

}
