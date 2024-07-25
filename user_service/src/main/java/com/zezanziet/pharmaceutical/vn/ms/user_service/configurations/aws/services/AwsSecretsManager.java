package com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.aws.services;

import com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.aws.AwsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.protocols.jsoncore.JsonNode;
import software.amazon.awssdk.protocols.jsoncore.JsonNodeParser;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Component
public class AwsSecretsManager {

    private final SecretsManagerClient secretsManagerClient;
    private final AwsProperties awsProperties;

    @Autowired
    public AwsSecretsManager(SecretsManagerClient secretsManagerClient, AwsProperties awsProperties) {
        this.secretsManagerClient = secretsManagerClient;
        this.awsProperties = awsProperties;
    }

    public String getSecretKey() {
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(awsProperties.getSecretKeyName())
                .build();

        try {

            GetSecretValueResponse getSecretValueResponse = secretsManagerClient.getSecretValue(getSecretValueRequest);

            String secretString = getSecretValueResponse.secretString();

            // todo: Parse the JSON string to extract the secret key value
            JsonNode jsonNode = JsonNodeParser.create().parse(secretString);

            return jsonNode.asObject().get("JWT_SecretKey").asString();

        } catch (SecretsManagerException e) {

            System.err.println(e.awsErrorDetails().errorMessage());

            throw e;
        }
    }

}
