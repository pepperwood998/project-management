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

import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;
import com.tuan.exercise.projman.pojo.JsonSingleData;
import com.tuan.exercise.projman.pojo.JsonPageableData;
import com.tuan.exercise.projman.pojo.ModulePojo;
import com.tuan.exercise.projman.pojo.Pagination;
import com.tuan.exercise.projman.service.ModuleService;

@RestController
@RequestMapping("/api/service")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping(value = "/list-by-env-ns")
    public ResponseEntity<JsonPageableData<Module>> getModuleListByEnvAndNs(
            @RequestParam(name = "environment") String env,
            @RequestParam(name = "namespace") String ns,
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        JsonPageableData<Module> result = new JsonPageableData<>();

        List<Module> moduleList = moduleService.findAllByEnvironmentAndNamespace(env, ns, start, size);
        if (moduleList.isEmpty()) {
            result.setMessage("No Module found");
        } else {
            result.setMessage("Found Modules");
        }
        Pagination pagination = new Pagination();
        pagination.setTotal(moduleService.countByEnvironmentAndNamespace(env, ns));
        pagination.setOffset(start);
        pagination.setSize(moduleList.size());

        result.setData(moduleList);
        result.setPagination(pagination);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/list-version-by-name")
    public ResponseEntity<JsonPageableData<String>> getModuleListByEnvAndNs(@RequestParam(name = "name") String name) {
        JsonPageableData<String> result = new JsonPageableData<>();

        List<String> versionList = moduleService.findAllVersionByName(name);
        if (versionList.isEmpty()) {
            result.setMessage("No Version found");
        } else {
            result.setMessage("Found Versions");
        }
        result.setData(versionList);
        result.setPagination(null);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<JsonSingleData> addNewService(@RequestBody ModulePojo payload)
            throws DuplicateServiceNameException {
        Module newModule = new Module(payload);

        moduleService.create(newModule);

        JsonSingleData result = new JsonSingleData();
        result.setMessage("New Service Saved");
        result.setData(newModule);
        return ResponseEntity.ok(result);
    }
}
