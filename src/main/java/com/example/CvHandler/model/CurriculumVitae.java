package com.example.CvHandler.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;


    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private Set<Activity> activities;


    public void setCandidat(Candidat candidat) {
        log.warn("SET CANDIDAT ON CV ENTITY");

        this.candidat = candidat;
        if (candidat != null && !candidat.getCv().equals(this)) {
            candidat.setCv(this);
        }
    }

}