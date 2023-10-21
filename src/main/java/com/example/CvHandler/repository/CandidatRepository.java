package com.example.CvHandler.repository;

import com.example.CvHandler.model.Candidat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends CrudRepository< Candidat,Long> {
}
