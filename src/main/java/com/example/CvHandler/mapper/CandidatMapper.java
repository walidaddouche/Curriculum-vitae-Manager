package com.example.CvHandler.mapper;

import com.example.CvHandler.dto.CandidatDTO;
import com.example.CvHandler.model.Candidat;

public class CandidatMapper {

    public static CandidatDTO candidatToDTO(Candidat candidat) {
        CandidatDTO candidatDTO = new CandidatDTO();
        candidatDTO.setNom(candidat.getNom());
        candidatDTO.setPrenom(candidat.getPrenom());
        candidatDTO.setEmail(candidat.getEmail());
        candidatDTO.setSiteWeb(candidat.getSiteWeb());
        candidatDTO.setDateNaissance(candidat.getDateNaissance());
        return candidatDTO;
    }

    public static Candidat dtoToCandidat(CandidatDTO candidatDTO) {
        Candidat candidat = new Candidat();
        candidat.setNom(candidatDTO.getNom());
        candidat.setPrenom(candidatDTO.getPrenom());
        candidat.setEmail(candidatDTO.getEmail());
        candidat.setSiteWeb(candidatDTO.getSiteWeb());
        candidat.setDateNaissance(candidatDTO.getDateNaissance());
        return candidat;
    }
}
