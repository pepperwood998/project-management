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
import org.springframework.web.bind.annotation.RestController;

import com.tuan.exercise.projman.config.Constant;
import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;
import com.tuan.exercise.projman.exception.MissingRequestBodyField;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.JsonDataResponse;
import com.tuan.exercise.projman.pojo.ModulePojo;
import com.tuan.exercise.projman.pojo.Pagination;
import com.tuan.exercise.projman.service.ModuleService;

@RestController
@RequestMapping("/api/service")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping(value = "/list")
    public ResponseEntity<JsonDataResponse> getModuleList(
            @RequestParam(name = "start", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            ModulePojo payload) {
        JsonDataResponse result = new JsonDataResponse();

        List<Module> moduleList = moduleService.findAllDynamic(payload, page, size);
        if (moduleList.isEmpty()) {
            result.setMessage(Constant.getServiceResultEmpty());
        } else {
            result.setMessage(Constant.getServiceResultOK());
        }
        Pagination pagination = new Pagination(
                moduleService.countDynamic(payload),
                page,
                moduleList.size());

        result.setData(moduleList);
        result.setPagination(pagination);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/list-version")
    public ResponseEntity<JsonDataResponse> getVersionList(@RequestParam(name = "name") String name) {
        JsonDataResponse result = new JsonDataResponse();

        List<String> versionList = moduleService.findAllVersions(name);
        if (versionList.isEmpty()) {
            result.setMessage(Constant.getServiceVersionResultEmpty());
        } else {
            result.setMessage(Constant.getServiceVersionResultOK());
        }
        result.setData(versionList);
        result.setPagination(null);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<JsonDataResponse> addNewService(
            @Valid @RequestBody ModulePojo payload, Errors errors)
            throws DuplicateServiceNameException, MissingRequestBodyField, ReleaseNotFoundException {
        if (errors.hasErrors()) {
            throw new MissingRequestBodyField(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Module addModule = moduleService.create(payload);

        JsonDataResponse result = new JsonDataResponse();
        result.setMessage(Constant.getServiceAddOK());
        result.setData(addModule);
        return ResponseEntity.ok(result);
    }
}
