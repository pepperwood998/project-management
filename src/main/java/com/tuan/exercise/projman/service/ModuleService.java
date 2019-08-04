package com.tuan.exercise.projman.service;

import java.util.List;

import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ModulePojo;

public interface ModuleService {

    List<Module> findAllDynamic(ModulePojo pojo, int pageInd, int pageSize);

    List<String> findAllVersions(String name);

    Module create(ModulePojo pojo) throws DuplicateServiceNameException, ReleaseNotFoundException;

    long countDynamic(ModulePojo pojo);
}
