package com.tuan.exercise.projman.service;

import java.util.List;

import com.tuan.exercise.projman.entity.Release;
import com.tuan.exercise.projman.exception.DuplicateReleaseVersionNameException;
import com.tuan.exercise.projman.exception.MissingRequestBodyField;
import com.tuan.exercise.projman.exception.ReleaseNotFoundException;
import com.tuan.exercise.projman.pojo.ReleasePojo;

public interface ReleaseService {

    List<Release> findAllDynamic(ReleasePojo release, int pageInd, int pageSize);

    Release create(ReleasePojo release) throws DuplicateReleaseVersionNameException;

    Release delete(String releaseId) throws ReleaseNotFoundException;

    Release update(ReleasePojo release)
            throws DuplicateReleaseVersionNameException, ReleaseNotFoundException, MissingRequestBodyField;

    long countDynamic(ReleasePojo pojo);
}
