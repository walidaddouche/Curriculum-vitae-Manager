package com.example.CvHandler.repository;

import com.example.CvHandler.model.CV;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CvRepository extends CrudRepository<CV,Long> {

    CV save(CV cv);

    void deleteById(Long id);

    Optional<CV> findById(Long id);






}
