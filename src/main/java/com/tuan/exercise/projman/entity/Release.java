package com.tuan.exercise.projman.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tuan.exercise.projman.pojo.ReleasePojo;

@Entity
@Table(name = "Release")
public class Release {

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

    public String getCreatedAt() {
        return createdAt == null ? null : createdAt.toString();
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

    public static Release newMapping(ReleasePojo pojo) {
        pojo.setCreatedAt(LocalDateTime.now());
        return cloneMapping(pojo);
    }

    public static Release cloneMapping(ReleasePojo pojo) {
        Release release = new Release();

        release.setId(pojo.getId());
        release.setName(pojo.getName());
        release.setDescription(pojo.getDescription());
        release.setCreatedAt(pojo.getCreatedAt());
        release.setCreatedBy(pojo.getCreatedBy());

        return release;
    }

    public static Release updateMapping(Release updatedRelease, ReleasePojo pojo) {
        updatedRelease.setName(pojo.getName());
        updatedRelease.setDescription(pojo.getDescription());
        updatedRelease.setCreatedBy(pojo.getCreatedBy());

        return updatedRelease;
    }
}
