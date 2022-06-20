package pl.mrotko.integromat.core.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Objects;

public class AppConfig {

    public static final Configuration CONFIG;

    static {
        Configurations configs = new Configurations();

        try {
            var propertiesPath = Objects.requireNonNull(System.getProperty("properties.path"), "required variable is not set: properties.path");
            CONFIG = configs.properties(propertiesPath);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
