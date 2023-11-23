package com.example.CvHandler.controller;

import com.example.CvHandler.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    ActivityService activityService;
    @Autowired
    ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }





}
