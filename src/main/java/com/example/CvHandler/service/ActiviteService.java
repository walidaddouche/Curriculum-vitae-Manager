package com.example.CvHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CvHandler.model.Activite;
import com.example.CvHandler.repository.ActiviteRepository;

import java.util.Optional;

@Service
public class ActiviteService {

    private final ActiviteRepository activiteRepository;

    @Autowired
    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public Activite saveActivite(Activite activite) {
        return activiteRepository.save(activite);
    }

    public Optional<Activite> getActiviteById(Long id) {
        return activiteRepository.findById(id);
    }

    public Iterable<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
}
