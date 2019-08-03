package com.tuan.exercise.projman.service;

import java.util.List;

import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;

public interface ModuleService {

    List<Module> findAllByEnvironmentAndNamespace(
            String environment, String namespace, int pageInd, int pageSize);

    List<String> findAllVersionByName(String name);

    Module create(Module module) throws DuplicateServiceNameException;

    long countByEnvironmentAndNamespace(String env, String ns);
}
