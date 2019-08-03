package com.tuan.exercise.projman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.JsonDataWrapper;
import com.tuan.exercise.projman.pojo.ReleaseCriteria;
import com.tuan.exercise.projman.service.ReleaseService;

@RestController
@RequestMapping("/api/release")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping(value = "/list")
    public ResponseEntity<JsonDataWrapper> getReleaseList(
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        JsonDataWrapper result = new JsonDataWrapper();

        List<Release> releaseList = releaseService.findAll(start, size);

        if (releaseList.isEmpty()) {
            result.setMessage("No Release found");
        } else {
            result.setMessage("Found Releases");
        }
        result.setData(releaseList);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<JsonDataWrapper> addNewRelease(@RequestBody ReleaseCriteria payload) {
        Release release = new Release(payload);

        releaseService.create(release);

        JsonDataWrapper result = new JsonDataWrapper();
        result.setMessage("New Release Saved");
        result.setData(release);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<JsonDataWrapper> updateRelease(@RequestBody ReleaseCriteria payload)
            throws DuplicateReleaseVersionNameException, ReleaseNotFoundException {
        Release newRelease = releaseService.update(payload);

        JsonDataWrapper result = new JsonDataWrapper();
        result.setMessage("A Release Updated");
        result.setData(newRelease);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<JsonDataWrapper> deleteRelease(@RequestParam(name = "id") String releaseId)
            throws ReleaseNotFoundException {
        Release release = releaseService.delete(releaseId);

        JsonDataWrapper result = new JsonDataWrapper();
        result.setMessage("A Release Deleted");
        result.setData(release);
        return ResponseEntity.ok(result);
    }
}
