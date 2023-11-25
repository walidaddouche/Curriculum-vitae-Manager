package com.example.CvHandler.dto;

import com.example.CvHandler.model.CurriculumVitae;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CandidatDTO {
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String siteWeb;
    private Date dateNaissance;
    CurriculumVitae cv;
}