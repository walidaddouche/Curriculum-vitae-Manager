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
                    ){
        this.candidatRepository = candidatRepository;
        this.curriculumVitaeRepository = curriculumVitaeRepository;
        this.activityRepository = activityRepository;
    }


    public void generateAndSaveCandidats(int numberOfCandidats) {
        Faker faker = new Faker();

        for (int i = 1; i <= numberOfCandidats; i++) {
            String nom = faker.name().lastName();
            String prenom = faker.name().firstName();
            String email = faker.internet().emailAddress();
            String webAddress = faker.internet().url();

            Date dateNaissance = faker.date().birthday();

            Candidat candidat = Candidat.builder()
                    .nom(nom)
                    .prenom(prenom)
                    .email(email)
                    .dateNaissance(dateNaissance)
                    .siteWeb(webAddress)
                    .build();




            candidatRepository.save(candidat);
            // Enregistrement du candidat dans le repository Candidat

            // Création du CV et des activités pour chaque candidat
            CurriculumVitae cv = generateCurriculumVitae(faker);


            candidat.setCv(cv);

            curriculumVitaeRepository.save(cv);
            candidatRepository.save(candidat);


            // Enregistrement du CV dans le repository CvRepository

            log.info("CANDIDAT du cv  " + cv.getCandidat());
            log.info("cv du candidat  " + candidat.getCv());

            Set<Activity> activities = cv.getActivities();
            for (Activity activity : activities) {
                activity.setCv(cv);
                // Enregistrement de chaque activité dans le repository ActivityRepository
                activityRepository.save(activity);
            }
        }

}

    public CurriculumVitae generateCurriculumVitae(Faker faker) {
        return CurriculumVitae.builder()
                .activities(generateActivities(faker))
                .build();
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
    public void run(String... args)  {

        int numberOfCandidats = 100;

        // Remplacez les paramètres par les instances appropriées de vos repositories
        this.generateAndSaveCandidats(numberOfCandidats);
    }
    }

