package com.example.CvHandler.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String siteWeb;
    private Date dateNaissance;
    private String motDePasse;
    @OneToOne(mappedBy = "candidat", cascade = CascadeType.ALL)
    private CurriculumVitae cv;


    public void updateAttributes(Candidat updatedCandidat) {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                // Rendre le champ accessible (s'il est private/protected)
                field.setAccessible(true);

                // Vérifier si le champ n'est pas nul dans l'objet mis à jour
                Object updatedValue = field.get(updatedCandidat);
                if (updatedValue != null) {
                    // Mettre à jour le champ dans l'objet actuel
                    field.set(this, updatedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Gérer l'exception selon votre besoin
            }
        }
    }

}
