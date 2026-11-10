package com.jcaa.usersmanagement.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class AppProperties {

  private static final String PROPERTIES_FILE = "application.properties";
  private static final String LOCAL_PROPERTIES_FILE = "application-local.properties";

  private final Properties properties;

  public AppProperties() {
    this.properties = loadDefaultProperties();
  }

  // Package-private: punto de entrada para pruebas unitarias.
  AppProperties(final InputStream stream) {
    this.properties = doLoad(stream);
  }

  private static Properties loadDefaultProperties() {
    final ClassLoader classLoader = AppProperties.class.getClassLoader();

    // Cargar configuración general.
    final Properties props = doLoad(
            classLoader.getResourceAsStream(PROPERTIES_FILE)
    );

    // Cargar configuración local, si existe.
    try (InputStream localStream =
                 classLoader.getResourceAsStream(LOCAL_PROPERTIES_FILE)) {

      if (localStream != null) {
        props.load(localStream);
      }

    } catch (final IOException exception) {
      throw ConfigurationException.becauseLoadFailed(exception);
    }

    return props;
  }

  private static Properties doLoad(final InputStream stream) {
    Objects.requireNonNull(
            stream,
            "File not found in classpath: " + PROPERTIES_FILE
    );

    final Properties props = new Properties();

    try (stream) {
      props.load(stream);
    } catch (final IOException exception) {
      throw ConfigurationException.becauseLoadFailed(exception);
    }

    return props;
  }

  public String get(final String key) {
    final String value = properties.getProperty(key);

    Objects.requireNonNull(
            value,
            "Property not found in " + PROPERTIES_FILE + ": " + key
    );

    return value;
  }

  public int getInt(final String key) {
    return Integer.parseInt(get(key));
  }
}
