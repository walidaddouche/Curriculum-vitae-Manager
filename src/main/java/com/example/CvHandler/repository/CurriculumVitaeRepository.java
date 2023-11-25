package com.example.CvHandler.repository;

import com.example.CvHandler.model.CurriculumVitae;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurriculumVitaeRepository extends CrudRepository<CurriculumVitae,Long> {


}
