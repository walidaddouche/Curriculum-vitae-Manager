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

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Iterable<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
