package com.example.CvHandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CvHandler.model.Activity;
import com.example.CvHandler.repository.ActivityRepository;

import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity saveActivite(Activity activite) {
        return activityRepository.save(activite);
    }

    public Optional<Activity> getActiviteById(Long id) {
        return activityRepository.findById(id);
    }

    public Iterable<Activity> getAllActivites() {
        return activityRepository.findAll();
    }

    public void deleteActivite(Long id) {
        activityRepository.deleteById(id);
    }
}
