package com.example.CvHandler;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CandidatRepository candidatRepository;

    @Autowired
    DatabaseLoader(CandidatRepository candidatRepository){
        this.candidatRepository = candidatRepository;
    }

    @Override
    public void run(String... args)  {

        String nom,prenom,email;
        Date dateNaissance;

        Faker faker = new Faker();
        for (int i = 1; i <= 100; i++) {
             nom = faker.name().lastName();
             prenom = faker.name().firstName();
             email = faker.internet().emailAddress();
             dateNaissance = faker.date().birthday();


            candidatRepository.save(
                                    Candidat.builder()
                                        .nom(nom)
                                        .prenom(prenom)
                                        .email(email)
                                        .dateNaissance(dateNaissance)
                                        .build()
                                    );
        }
    }
    }

