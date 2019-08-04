package com.tuan.exercise.projman.pojo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class ReleasePojo {

    private String id;
    @NotBlank(message = "{release.missing.name}")
    private String name;
    private String description;
    private LocalDateTime createdAt;
    @NotBlank(message = "{release.missing.author}")
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
