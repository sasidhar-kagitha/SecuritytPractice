package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3ClientConfig {

    @Value("${aws.accesskey}")
    private String accesskey;

    @Value("${aws.secretkey}")
    private String secretkey;


    @Bean
    public S3Client client()
    {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accesskey,secretkey);
        return  S3Client
                .builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
