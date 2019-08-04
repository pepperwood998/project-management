package com.tuan.exercise.projman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.config.Constant;
import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.MissingRequestBodyField;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ReleasePojo;
import com.tuan.exercise.projman.repository.ReleaseRepository;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    private int pagingLimit = Constant.getPagingLimit();
    private ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id", "createdAt", "description");

    @Override
    public List<Release> findAllDynamic(ReleasePojo pojo, int pageInd, int pageSize) {
        pageSize = pageSize > pagingLimit ? pagingLimit : pageSize;

        Release release = Release.cloneMapping(pojo);
        Example<Release> releaseEx = Example.of(release, exampleMatcher);

        return releaseRepository.findAll(releaseEx, 
                PageRequest.of(pageInd, pageSize, Direction.ASC, "createdAt")).getContent();
    }

    @Override
    public Release create(ReleasePojo pojo) throws DuplicateReleaseVersionNameException {
        if (releaseRepository.existsByName(pojo.getName()))
            throw new DuplicateReleaseVersionNameException(pojo.getName() + Constant.getReleaseAlreadyExisted());

        Release release = Release.newMapping(pojo);
        return releaseRepository.save(release);
    }

    @Override
    public Release delete(String releaseId) throws ReleaseNotFoundException {
        Release release = releaseRepository.findById(releaseId).orElse(null);
        if (release == null)
            throw new ReleaseNotFoundException(Constant.getReleaseNotExist());

        releaseRepository.deleteById(releaseId);

        return release;
    }

    @Override
    public Release update(ReleasePojo pojo)
            throws DuplicateReleaseVersionNameException, ReleaseNotFoundException, MissingRequestBodyField {
        String releaseId = pojo.getId();
        if (releaseId == null)
            throw new MissingRequestBodyField(Constant.getReleaseMissingId());

        Release release = releaseRepository.findById(pojo.getId()).orElse(null);
        if (release == null)
            throw new ReleaseNotFoundException(Constant.getReleaseNotExist());

        if (releaseRepository.existsByNameForUpdate(pojo.getId(), pojo.getName())) {
            throw new DuplicateReleaseVersionNameException(pojo.getName() + Constant.getReleaseAlreadyExisted());
        }

        return releaseRepository.save(Release.updateMapping(release, pojo));
    }

    @Override
    public long countDynamic(ReleasePojo pojo) {
        Release release = Release.newMapping(pojo);
        Example<Release> releaseEx = Example.of(release, exampleMatcher);

        return releaseRepository.count(releaseEx);
    }
}
