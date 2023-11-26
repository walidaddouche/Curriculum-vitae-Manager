package com.example.CvHandler.service;


import com.example.CvHandler.summary.CandidatProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    public Page<CandidatProjection> searchByNom(Pageable pageable, String nom) {
        return candidatRepository.findByNomContainingIgnoreCase(pageable,nom);
    }

    public Page<CandidatProjection> searchByPrenom(Pageable pageable, String prenom) {
        return candidatRepository.findByPrenomContainingIgnoreCase(pageable,prenom);
    }

    public Page<CandidatProjection> searchByNomAndPrenom(Pageable pageable, String nom, String prenom) {
        return candidatRepository.findByNomContainingAndPrenomContainingIgnoreCase(pageable,nom, prenom);
    }

    public Candidat createCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public boolean isEmailUnique(String email) {
        Optional<Candidat> existingCandidat = candidatRepository.findByEmail(email);
        return existingCandidat.isEmpty();
    }




}
