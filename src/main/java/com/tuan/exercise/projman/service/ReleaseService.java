package com.tuan.exercise.projman.service;

import java.util.List;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ReleaseCriteria;

public interface ReleaseService {

    List<Release> findAll(int pageInd, int pageSize);

    Release create(Release release);

    Release delete(String releaseId) throws ReleaseNotFoundException;

    Release update(ReleaseCriteria release) throws DuplicateReleaseVersionNameException, ReleaseNotFoundException;
}
