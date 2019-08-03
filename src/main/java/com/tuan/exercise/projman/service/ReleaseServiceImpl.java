package com.tuan.exercise.projman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.config.Constant;
import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ReleasePojo;
import com.tuan.exercise.projman.repository.ReleaseRepository;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public List<Release> findAll(int pageInd, int pageSize) {
        if (pageSize > Constant.pagingLimit)
            pageSize = Constant.pagingLimit;
        
        return releaseRepository.findAll(PageRequest.of(pageInd, pageSize, Direction.ASC, "createdAt")).getContent();
    }

    @Override
    public Release create(Release release) {
        return releaseRepository.save(release);
    }

    @Override
    public Release delete(String releaseId) throws ReleaseNotFoundException {
        Release release = releaseRepository.findById(releaseId).orElse(null);
        if (release == null)
            throw new ReleaseNotFoundException("The Release is not exist");
        
        releaseRepository.deleteById(releaseId);

        return release;
    }

    @Override
    public Release update(ReleasePojo release) throws DuplicateReleaseVersionNameException, ReleaseNotFoundException {
        if (releaseRepository.existsByName(release.getName())) {
            throw new DuplicateReleaseVersionNameException("A Release with name " + release.getName() +" has already existed");
        }

        Release newRelease = releaseRepository.findById(release.getId()).orElse(null);

        if (newRelease == null)
            throw new ReleaseNotFoundException("The Release is not exist");

        newRelease.update(release);
        newRelease = releaseRepository.save(newRelease);

        return newRelease;
    }

    @Override
    public long countAll() {
        return releaseRepository.count();
    }
}
