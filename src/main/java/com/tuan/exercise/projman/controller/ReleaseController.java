package com.tuan.exercise.projman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.entity.ReleaseCriteria;
import com.tuan.exercise.projman.service.ReleaseService;

@RestController
@RequestMapping("/api/release")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @PostMapping(value = "/create")
    public Release addNewRelease(@RequestBody ReleaseCriteria payload) {
        Release release = new Release(payload);

        releaseService.save(release);
        return release;
    }
}
