package com.tuan.exercise.projman.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.repository.ReleaseRepository;

@RestController
@RequestMapping("/release")
public class ReleaseController {

    @Autowired
    private ReleaseRepository releaseRepository;

    @GetMapping("/add")
    public Release addNewRelease() {
        Release release = new Release();
        release.setName("1.0.0");
        release.setDescription("Description for Release 1.0.0");
        release.setCreatedAt(LocalDateTime.now());
        release.setCreatedBy("TuanDT");

        releaseRepository.addRelease(release);
        return release;
    }
}
