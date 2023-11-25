package com.example.CvHandler.service;


import com.example.CvHandler.summary.CandidatProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }


    public Optional<Candidat> getCandidatById(Long id) {
        return candidatRepository.findById(id);
    }


    public Iterable<Candidat> getAllCandidats() {
        return candidatRepository.findAll();

    }

    public Iterable<Candidat> getAllCandidats(Pageable pageable) {
        return candidatRepository.findAll(pageable);

    }

    public void deleteCandidat(Long id) {
        candidatRepository.deleteById(id);
    }

    public Candidat updateCandidatAttributes(Long candidatId, Candidat updatedCandidat) {
        Candidat existingCandidat = candidatRepository.findById(candidatId).orElse(null);

        if (existingCandidat != null) {
            existingCandidat.updateAttributes(updatedCandidat);
            return candidatRepository.save(existingCandidat);
        }

        return null; // Gérer le cas où le candidat n'est pas trouvé
    }


    public List<CandidatProjection> getAllCandidatWithoutDetails(Pageable pageable) {

        return candidatRepository.getAllCandidatWithoutDetails(pageable).toList();

    }
    public long countAllCandidats() {
        return candidatRepository.count();
    }


    public List<Candidat> searchByNom(String nom) {
        // Implémentez la logique de recherche par nom
        return candidatRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Candidat> searchByPrenom(String prenom) {
        // Implémentez la logique de recherche par prénom
        return candidatRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    public List<Candidat> searchByNomAndPrenom(String nom, String prenom) {
        // Implémentez la logique de recherche par nom et prénom
        return candidatRepository.findByNomContainingAndPrenomContainingIgnoreCase(nom, prenom);
    }




}
