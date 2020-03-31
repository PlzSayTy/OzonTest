package ru.appline.autotests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;

    private TestProperties() {
        try {
            properties.load(new FileInputStream(new File("./src/test/resources/" + System.getProperty("env", "test") + ".properties")));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static TestProperties getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
