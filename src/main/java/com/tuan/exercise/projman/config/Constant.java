package com.tuan.exercise.projman.config;

import java.io.FileReader;
import java.util.Properties;

public class Constant {

    public static int pagingLimit;

    static {

        try (FileReader reader = new FileReader("./src/main/resources/global.properties")) {
            Properties p = new Properties();
            p.load(reader);

            pagingLimit = Integer.valueOf(p.getProperty("paging-limit"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
