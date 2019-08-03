package com.tuan.exercise.projman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.entity.Module;
import com.tuan.exercise.projman.exception.DuplicateServiceNameException;
import com.tuan.exercise.projman.repository.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> findAllByEnvironmentAndNamespace(
            String environment, String namespace, int pageInd, int pageSize) {
        return moduleRepository.findAllByEnvironmentAndNamespace(
                environment, namespace, PageRequest.of(pageInd, pageSize));
    }

    @Override
    public List<String> findAllVersionByName(String name) {
        return moduleRepository.findAllVersionListByName(name);
    }

    @Override
    public Module create(Module module) throws DuplicateServiceNameException {
        if (moduleRepository.existsByName(module.getName()))
            throw new DuplicateServiceNameException("A Service with name " + module.getName() + " has already existed");

        return moduleRepository.save(module);
    }
}
