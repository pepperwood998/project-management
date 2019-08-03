package com.tuan.exercise.projman.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Release;

@Repository
public interface ReleaseRepository extends PagingAndSortingRepository<Release, String> {

    @Query("select case "
            + "when count(r) > 0 then 'true' else 'false' end "
            + "from Release r where r.name = :name")
    public boolean existsByName(String name);
}
