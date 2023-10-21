package com.example.CvHandler.controller;

import com.example.CvHandler.service.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activites")
public class ActiviteController {
    ActiviteService activiteService;
    @Autowired
    ActiviteController(ActiviteService activiteService){
        this.activiteService = activiteService;
    }


}
