package com.tuan.exercise.projman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.config.Constant;
import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ModulePojo;
import com.tuan.exercise.projman.repository.ModuleRepository;
import com.tuan.exercise.projman.repository.ReleaseRepository;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ReleaseRepository releaseRepository;

    private int pagingLimit = Constant.getPagingLimit();
    private ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");

    @Override
    public List<Module> findAllDynamic(ModulePojo pojo, int pageInd, int pageSize) {
        pageSize = pageSize > pagingLimit ? pagingLimit : pageSize;

        Module module = Module.cloneMapping(pojo);
        Example<Module> moduleEx = Example.of(module, exampleMatcher);

        return moduleRepository.findAll(moduleEx, 
                PageRequest.of(pageInd, pageSize)).getContent();
    }

    @Override
    public List<String> findAllVersions(String name) {
        return moduleRepository.findAllVersionsByName(name);
    }

    @Override
    public Module create(ModulePojo pojo) throws DuplicateServiceNameException, ReleaseNotFoundException {
        if (releaseRepository.findById(pojo.getReleaseId()).orElse(null) == null)
            throw new ReleaseNotFoundException(Constant.getReleaseNotExist());
        
        if (moduleRepository.existsByNameForCreate(pojo.getName(), pojo.getReleaseId()))
            throw new DuplicateServiceNameException(pojo.getName() + Constant.getServiceAlreadyExisted());

        Module newModule = Module.newMapping(pojo);

        return moduleRepository.save(newModule);
    }

    @Override
    public long countDynamic(ModulePojo pojo) {
        Module module = Module.newMapping(pojo);
        Example<Module> moduleEx = Example.of(module, exampleMatcher);

        return moduleRepository.count(moduleEx);
    }
}
