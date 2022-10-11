package com.org.promotion.configs;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class PromotionExternalConfiguration {
    private static class HelperHolder {
        protected static final PromotionExternalConfiguration externalConfig = new PromotionExternalConfiguration();
    }

    private PromotionConfigProperties configurationProperties;

    private PromotionExternalConfiguration() {
        initConfigurationProperties();
    }

    public static PromotionExternalConfiguration getInstance() {
        return HelperHolder.externalConfig;
    }

    public PromotionConfigProperties getConfigurationProperties() {
        return configurationProperties;
    }

    private void initConfigurationProperties() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            //mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            InputStream inputStream = PromotionExternalConfiguration.class.getClassLoader().getResourceAsStream("config.yaml");
            this.configurationProperties = mapper.readValue(inputStream, PromotionConfigProperties.class);
        } catch (IOException exception) {
            throw new IllegalStateException("Config not initialized properly.");
        }
    }
}
