package com.tuan.exercise.projman.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Release;

@Repository
public interface ReleaseRepository extends PagingAndSortingRepository<Release, String> {

}
