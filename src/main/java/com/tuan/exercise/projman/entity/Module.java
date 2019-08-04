package com.tuan.exercise.projman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tuan.exercise.projman.pojo.ModulePojo;

@Entity
@Table(name = "Service")
public class Module {

    @Id
    @GenericGenerator(name = "release_id", strategy = "com.tuan.exercise.projman.util.GeneralIdentifierGenerator")
    @GeneratedValue(generator = "release_id")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "environment")
    private String environment;

    @Column(name = "namespace")
    private String namespace;

    @Column(name = "oldVersion")
    private String oldVersion;

    @Column(name = "newVersion")
    private String newVersion;

    @Column(name = "releaseId")
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

    public static Module newMapping(ModulePojo pojo) {
        pojo.setOldVersion("0.0.0");
        return cloneMapping(pojo);
    }

    public static Module cloneMapping(ModulePojo pojo) {
        Module module = new Module();

        module.setId(pojo.getId());
        module.setName(pojo.getName());
        module.setEnvironment(pojo.getEnvironment());
        module.setNamespace(pojo.getNamespace());
        module.setOldVersion(pojo.getOldVersion());
        module.setNewVersion(pojo.getNewVersion());
        module.setReleaseId(pojo.getReleaseId());

        return module;
    }
}
