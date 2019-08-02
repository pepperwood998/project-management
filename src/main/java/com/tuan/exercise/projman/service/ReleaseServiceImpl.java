package com.tuan.exercise.projman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.repository.ReleaseRepository;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public Release save(Release release) {
        return releaseRepository.save(release);
    }
}
