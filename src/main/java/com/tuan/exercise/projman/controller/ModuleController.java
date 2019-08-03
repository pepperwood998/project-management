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
import com.tuan.exercise.projman.pojo.JsonDataWrapper;
import com.tuan.exercise.projman.pojo.ModulePojo;
import com.tuan.exercise.projman.service.ModuleService;

@RestController
@RequestMapping("/api/service")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping(value = "/list-by-env-ns")
    public ResponseEntity<JsonDataWrapper> getModuleListByEnvAndNs(
            @RequestParam(name = "environment") String env,
            @RequestParam(name = "namespace") String ns,
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        JsonDataWrapper result = new JsonDataWrapper();

        List<Module> moduleList = moduleService.findAllByEnvironmentAndNamespace(env, ns, start, size);
        if (moduleList.isEmpty()) {
            result.setMessage("No Module found");
        } else {
            result.setMessage("Found Modules");
        }
        result.setData(moduleList);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping(value = "/list-version-by-name")
    public ResponseEntity<JsonDataWrapper> getModuleListByEnvAndNs(@RequestParam(name = "name") String name) {
        JsonDataWrapper result = new JsonDataWrapper();
        
        List<String> versionList = moduleService.findAllVersionByName(name);
        if (versionList.isEmpty()) {
            result.setMessage("No Version found");
        } else {
            result.setMessage("Found Versions");
        }
        result.setData(versionList);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<JsonDataWrapper> addNewService(@RequestBody ModulePojo payload)
            throws DuplicateServiceNameException {
        Module newModule = new Module(payload);

        moduleService.create(newModule);

        JsonDataWrapper result = new JsonDataWrapper();
        result.setMessage("New Service Saved");
        result.setData(newModule);
        return ResponseEntity.ok(result);
    }
}
