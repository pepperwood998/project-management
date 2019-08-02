package com.tuan.exercise.projman.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Release")
public class Release {

    public Release() {
    }

    public Release(ReleaseCriteria releaseCriteria) {
        this.setName(releaseCriteria.getName());
        this.setDescription(releaseCriteria.getDescription());
        this.setCreatedAt(LocalDateTime.now());
        this.setCreatedBy(releaseCriteria.getCreatedBy());
    }

    @Id
    @GenericGenerator(name = "release_id", strategy = "com.tuan.exercise.projman.util.GeneralIdentifierGenerator")
    @GeneratedValue(generator = "release_id")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
