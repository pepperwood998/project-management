package com.tuan.exercise.projman.pojo;

import javax.validation.constraints.NotBlank;

public class ModulePojo {

    private String id;
    @NotBlank(message = "{service.missing.name}")
    private String name;
    @NotBlank(message = "{service.missing.environment}")
    private String environment;
    @NotBlank(message = "{service.missing.namespace}")
    private String namespace;
    private String oldVersion;
    @NotBlank(message = "{service.missing.new.version}")
    private String newVersion;
    @NotBlank(message = "{service.missing.release.id}")
    private String releaseId;

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

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(String oldVersion) {
        this.oldVersion = oldVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId;
    }
}
