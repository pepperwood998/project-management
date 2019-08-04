package com.tuan.exercise.projman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Module;

@Repository
public interface ModuleRepository
    extends PagingAndSortingRepository<Module, String>, QueryByExampleExecutor<Module> {

    @Query("select newVersion from Module as m where m.name = :name")
    public List<String> findAllVersionsByName(String name);

    public long countByEnvironmentAndNamespace(String environment, String namespace);

    public boolean existsByName(String name);

    @Query("select case "
            + "when count(m)> 0 then true else false end "
            + "from Module m where m.name = :name and m.releaseId = :releaseId")
    public boolean existsByNameForCreate(String name, String releaseId);
}
