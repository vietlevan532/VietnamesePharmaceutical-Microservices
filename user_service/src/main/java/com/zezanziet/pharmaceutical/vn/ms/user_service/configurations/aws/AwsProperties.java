package com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.aws;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {

    private String accessKeyId;
    private String secretAccessKey;
    private String region;
    private String jwtSecretKeyName;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getJwtSecretKeyName() {
        return jwtSecretKeyName;
    }

    public void setJwtSecretKeyName(String jwtSecretKeyName) {
        this.jwtSecretKeyName = jwtSecretKeyName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
}
