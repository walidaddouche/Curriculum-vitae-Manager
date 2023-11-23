package com.example.CvHandler;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;
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
        // Insérez des données ici
        Candidat candidat1 = Candidat.builder().nom("ALVAREZ").prenom("RAYAN").email("Rayan@Mail.com").build();
        Candidat candidat2 = Candidat.builder().nom("THOMAS").prenom("BROL").email("Thomas@Mail.com").dateNaissance(new Date("17/01/2001")).build();

        candidatRepository.save(candidat1);
        candidatRepository.save(candidat2);
    }
}
