package com.example.CvHandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {


    @RequestMapping("/")
    public ModelAndView app() {
        return new ModelAndView("app");
    }

}


