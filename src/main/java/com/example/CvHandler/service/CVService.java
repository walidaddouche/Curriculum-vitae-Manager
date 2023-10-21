package com.example.CvHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CvHandler.model.CV;
import com.example.CvHandler.repository.CvRepository;

import java.util.Optional;

@Service
public class CVService {

    private final CvRepository cvRepository;

    @Autowired
    public CVService(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public CV saveCV(CV cv) {
        return cvRepository.save(cv);
    }

    public Optional<CV> getCVById(Long id) {
        return cvRepository.findById(id);
    }

    public Iterable<CV> getAllCVs() {
        return cvRepository.findAll();
    }

    public void deleteCV(Long id) {
        cvRepository.deleteById(id);
    }

}
