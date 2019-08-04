package com.tuan.exercise.projman.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Release;

@Repository
public interface ReleaseRepository
        extends PagingAndSortingRepository<Release, String>, QueryByExampleExecutor<Release> {

    @Query("select case "
            + "when count(r)> 0 then true else false end "
            + "from Release r where r.id != :id and r.name = :name")
    public boolean existsByNameForUpdate(String id, String name);

    public boolean existsByName(String name);
}
