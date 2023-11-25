package com.example.CvHandler;

import com.example.CvHandler.model.Activity;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.model.CurriculumVitae;
import com.example.CvHandler.repository.ActivityRepository;
import com.example.CvHandler.repository.CandidatRepository;
import com.example.CvHandler.repository.CurriculumVitaeRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CandidatRepository candidatRepository;
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final ActivityRepository activityRepository;


    @Autowired
    DatabaseLoader(CandidatRepository candidatRepository,
                   CurriculumVitaeRepository curriculumVitaeRepository,
                   ActivityRepository activityRepository
    ) {
        this.candidatRepository = candidatRepository;
        this.curriculumVitaeRepository = curriculumVitaeRepository;
        this.activityRepository = activityRepository;
    }


    public void generateAndSaveCandidats() {
        // Création d'un CurriculumVitae
        CurriculumVitae cv = new CurriculumVitae();
        cv.setActivities(new HashSet<>());  // Vous pouvez ajouter des activités ici si nécessaire
        curriculumVitaeRepository.save(cv);

        // Création d'un Candidat avec le CurriculumVitae associé
        Candidat candidat = new Candidat();
        candidat.setNom("Nom du candidat");
        candidat.setPrenom("Prénom du candidat");
        candidat.setEmail("email@example.com");
        candidat.setDateNaissance(new Date());

        candidat.setCv(cv);

        candidatRepository.save(candidat);


    }

    public CurriculumVitae generateCurriculumVitae(Faker faker) {
        return null;
    }

    public Set<Activity> generateActivities(Faker faker) {
        Set<Activity> activities = new HashSet<>();
        int numberOfActivities = faker.random().nextInt(1, 5);

        for (int i = 0; i < numberOfActivities; i++) {
            Activity activity = Activity.builder()
                    .activityYear(faker.number().numberBetween(2000, 2023))
                    .nature(faker.lorem().word())
                    .title(faker.job().position())
                    .description(faker.lorem().sentence())
                    .adresseWeb(faker.internet().url())
                    .build();

            activities.add(activity);
        }

        return activities;
    }

    @Override
    public void run(String... args) {
        this.generateAndSaveCandidats();



        // Remplacez les paramètres par les instances appropriées de vos repositories
    }
}

