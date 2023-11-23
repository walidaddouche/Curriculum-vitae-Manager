package com.example.CvHandler.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;
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
}
