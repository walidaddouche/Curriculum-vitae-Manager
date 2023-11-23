package com.example.CvHandler.repository;

import com.example.CvHandler.model.CurriculumVitae;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumVitaeRepository extends CrudRepository<CurriculumVitae,Long> {



}
