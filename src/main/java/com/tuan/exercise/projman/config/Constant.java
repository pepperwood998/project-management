package com.tuan.exercise.projman.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constant {
    private static final Logger LOG = LoggerFactory.getLogger(Constant.class);
    private static final String CUSTOM_PROP_PATH = "./src/main/resources/global.properties";

    private Constant() {
    }

    private static int pagingLimit = 0;

    private static String releaseMissingId;
    private static String releaseNotExist;
    private static String releaseAlreadyExisted;

    private static String releaseResultOK;
    private static String releaseResultEmpty;
    private static String releaseAddOK;
    private static String releaseUpdateOK;
    private static String releaseDeleteOK;

    private static String serviceAlreadyExisted;

    private static String serviceResultOK;
    private static String serviceResultEmpty;
    private static String serviceAddOK;
    private static String serviceVersionResultOK;
    private static String serviceVersionResultEmpty;

    private static int idLength = 0;

    private static String exceptionIO = "Error handling IO";

    static {
        try (FileReader reader = new FileReader(CUSTOM_PROP_PATH)) {
            Properties p = new Properties();
            p.load(reader);

            pagingLimit = Integer.valueOf(p.getProperty("paging.limit"));

            releaseMissingId = p.getProperty("release.missing.id");
            releaseNotExist = p.getProperty("release.not.exist");
            releaseAlreadyExisted = " " + p.getProperty("release.already.existed");

            releaseResultOK = p.getProperty("release.result.ok");
            releaseResultEmpty = p.getProperty("release.result.empty");
            releaseAddOK = p.getProperty("release.add.ok");
            releaseUpdateOK = p.getProperty("release.update.ok");
            releaseDeleteOK = p.getProperty("release.delete.ok");

            serviceAlreadyExisted = " " + p.getProperty("service.already.existed");

            serviceResultOK = p.getProperty("service.result.ok");
            serviceResultEmpty = p.getProperty("service.result.empty");
            serviceAddOK = p.getProperty("service.add.ok");
            serviceVersionResultOK = p.getProperty("service.version.result.ok");
            serviceVersionResultEmpty = p.getProperty("service.version.result.empty");

            idLength = Integer.parseInt(p.getProperty("length.id"));
            exceptionIO = p.getProperty("io.exception");
        } catch (IOException e) {
            LOG.error(exceptionIO, e);
        }
    }

    public static int getPagingLimit() {
        return pagingLimit;
    }

    public static String getReleaseMissingId() {
        return releaseMissingId;
    }

    public static String getReleaseNotExist() {
        return releaseNotExist;
    }

    public static String getReleaseAlreadyExisted() {
        return releaseAlreadyExisted;
    }

    public static String getReleaseResultOK() {
        return releaseResultOK;
    }

    public static String getReleaseResultEmpty() {
        return releaseResultEmpty;
    }

    public static String getReleaseAddOK() {
        return releaseAddOK;
    }

    public static String getReleaseUpdateOK() {
        return releaseUpdateOK;
    }

    public static String getReleaseDeleteOK() {
        return releaseDeleteOK;
    }

    public static String getServiceAlreadyExisted() {
        return serviceAlreadyExisted;
    }

    public static String getServiceResultOK() {
        return serviceResultOK;
    }

    public static String getServiceResultEmpty() {
        return serviceResultEmpty;
    }

    public static String getServiceAddOK() {
        return serviceAddOK;
    }

    public static String getServiceVersionResultOK() {
        return serviceVersionResultOK;
    }

    public static String getServiceVersionResultEmpty() {
        return serviceVersionResultEmpty;
    }

    public static int getIdLength() {
        return idLength;
    }

    public static String getExceptionIO() {
        return exceptionIO;
    }
}
