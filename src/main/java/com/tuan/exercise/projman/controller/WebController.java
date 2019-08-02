package com.tuan.exercise.projman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(value = "/release/add-form")
    public String getReleaseAddForm() {
        return "release-form";
    }
}