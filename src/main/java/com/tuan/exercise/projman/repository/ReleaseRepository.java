package com.tuan.exercise.projman.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tuan.exercise.projman.entity.Release;

@Repository
public class ReleaseRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addRelease(Release release) {
        em.persist(release);
    }
}
