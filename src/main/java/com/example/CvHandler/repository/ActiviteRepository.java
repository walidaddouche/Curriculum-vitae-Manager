package com.example.CvHandler.repository;

import com.example.CvHandler.model.Activite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends CrudRepository<Activite,Long> {
}
