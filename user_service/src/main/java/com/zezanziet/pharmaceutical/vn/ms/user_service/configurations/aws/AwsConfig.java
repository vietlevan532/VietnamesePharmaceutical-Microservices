package com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.aws;

import com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.aws.services.AwsSecretsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class AwsConfig {
    @Bean
    public SecretsManagerClient secretsManagerClient(AwsProperties awsProperties) {
        Region region = Region.of(awsProperties.getRegion());
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                awsProperties.getAccessKeyId(),
                awsProperties.getSecretAccessKey()
        );
        return SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }

    @Bean
    public AwsSecretsManager awsSecretsManager(SecretsManagerClient secretsManagerClient, AwsProperties awsProperties) {
        return new AwsSecretsManager(secretsManagerClient, awsProperties);
    }
}
