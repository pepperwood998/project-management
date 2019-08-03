package com.tuan.exercise.projman.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Release;

@Repository
public interface ReleaseRepository extends PagingAndSortingRepository<Release, String> {

    public List<Release> findTop2ByOrderByCreatedAtDesc();

    public boolean existsByName(String name);
}
