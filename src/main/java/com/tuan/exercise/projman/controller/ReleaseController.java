package com.tuan.exercise.projman.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.exercise.projman.config.Constant;
import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.MissingRequestBodyField;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.JsonDataResponse;
import com.tuan.exercise.projman.pojo.Pagination;
import com.tuan.exercise.projman.pojo.ReleasePojo;
import com.tuan.exercise.projman.service.ReleaseService;

@RestController
@RequestMapping("/api/release")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping(value = "/list")
    public @ResponseBody ResponseEntity<JsonDataResponse> getReleaseList(
            @RequestParam(name = "start", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            ReleasePojo payload) {
        JsonDataResponse result = new JsonDataResponse();

        List<Release> releaseList = releaseService.findAllDynamic(payload, page, size);
        if (releaseList.isEmpty()) {
            result.setMessage(Constant.getReleaseResultEmpty());
        } else {
            result.setMessage(Constant.getReleaseResultOK());
        }
        Pagination pagination = new Pagination(
                releaseService.countDynamic(payload),
                page,
                releaseList.size());

        result.setData(releaseList);
        result.setPagination(pagination);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<JsonDataResponse> addNewRelease(
            @Valid @RequestBody ReleasePojo payload, Errors errors)
            throws DuplicateReleaseVersionNameException, MissingRequestBodyField {
        if (errors.hasErrors()) {
            throw new MissingRequestBodyField(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Release addedRelease = releaseService.create(payload);

        JsonDataResponse result = new JsonDataResponse();
        result.setMessage(Constant.getReleaseAddOK());
        result.setData(addedRelease);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<JsonDataResponse> updateRelease(
            @Valid @RequestBody ReleasePojo payload, Errors errors)
            throws DuplicateReleaseVersionNameException, ReleaseNotFoundException, MissingRequestBodyField {
        if (errors.hasErrors()) {
            throw new MissingRequestBodyField(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Release updatedRelease = releaseService.update(payload);

        JsonDataResponse result = new JsonDataResponse();
        result.setMessage(Constant.getReleaseUpdateOK());
        result.setData(updatedRelease);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<JsonDataResponse> deleteRelease(@RequestParam(name = "id") String releaseId)
            throws ReleaseNotFoundException {
        Release release = releaseService.delete(releaseId);

        JsonDataResponse result = new JsonDataResponse();
        result.setMessage(Constant.getReleaseDeleteOK());
        result.setData(release);
        return ResponseEntity.ok(result);
    }
}
