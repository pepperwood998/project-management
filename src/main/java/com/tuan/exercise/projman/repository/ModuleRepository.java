package com.tuan.exercise.projman.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Module;

@Repository
public interface ModuleRepository extends PagingAndSortingRepository<Module, String> {

    public boolean existsByName(String name);

    public Page<Module> findAllByEnvironmentAndNamespace(
            String environment, String namespace, Pageable pageable);

    @Query("select newVersion from Module as m where m.name = :name")
    public List<String> findAllVersionListByName(String name);

    public long countByEnvironmentAndNamespace(String environment, String namespace);
}
