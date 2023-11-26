package com.example.CvHandler;

import com.example.CvHandler.model.Activity;
import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.model.CurriculumVitae;
import com.example.CvHandler.model.ACTIVITY_TYPE;
import com.example.CvHandler.repository.ActivityRepository;
import com.example.CvHandler.repository.CandidatRepository;
import com.example.CvHandler.repository.CurriculumVitaeRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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
        Faker faker = new Faker();

        for (int i = 0; i < 1000; i++) {
            // Création d'un CurriculumVitae
            CurriculumVitae cv = CurriculumVitae.builder().build();
            Set<Activity> activities = generateActivities(faker);
            cv.setActivities(activities);

            // Création d'un Candidat avec le CurriculumVitae associé
            Candidat candidat = new Candidat();
            candidat.setNom(faker.name().lastName());
            candidat.setPrenom(faker.name().firstName());
            candidat.setEmail(faker.internet().emailAddress());
            candidat.setDateNaissance(faker.date().birthday());

            candidat.setCv(cv);
            activityRepository.saveAll(activities);

            curriculumVitaeRepository.save(cv);
            candidatRepository.save(candidat);
        }


    }
    public Set<Activity> generateActivities(Faker faker) {
        Set<Activity> activities = new HashSet<>();
        int numberOfActivities = faker.random().nextInt(1, 5);

        for (int i = 0; i < numberOfActivities; i++) {
            Activity activity = Activity.builder()
                    .activityYear(faker.number().numberBetween(2000, 2023))
                    .activityType(ACTIVITY_TYPE.randomNatureActivity())
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

