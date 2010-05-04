package com.redhat.rhn.harness.common.proximator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.redhat.rhn.harness.common.HarnessConfiguration;

public class ProxyScriptParser extends com.redhat.rhn.harness.common.HarnessConfiguration{
        private static Properties properties;

    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key, System.getProperty(key, defaultValue));
    }

    public static int getPropertyAsInt(String key, int defaultValue) {
        int intValue = -1;
        try {
            intValue = Integer.parseInt(getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return intValue;
    }

    private static void loadProperties() {
        properties = new Properties();
        String path = "/" + System.getProperty("harness.environment", "localhost") + "-proximator-settings.properties";
        InputStream in = null;
        try {
            // try class path
            in = HarnessConfiguration.class.getResourceAsStream(path);
            if (in == null) {
                // try relative file path
                File file = new File("src/main/resources" + path);
                if (file.exists()) {
                    in = new FileInputStream(file);
                }
            }
            if (in != null) {
                properties.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
}
