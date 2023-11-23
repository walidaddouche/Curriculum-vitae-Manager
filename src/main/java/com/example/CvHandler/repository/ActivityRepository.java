package com.example.CvHandler.repository;

import com.example.CvHandler.model.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity,Long> {
}
