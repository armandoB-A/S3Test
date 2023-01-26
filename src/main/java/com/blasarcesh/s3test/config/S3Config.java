package com.blasarcesh.s3test.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.access_key_id}")
    private String accesskeyId;
    @Value("${aws.secret_access_key1}")
    private String accessSecretKey;
    @Value("${aws.s3.region}")
    private String region;
    @Bean
    public AmazonS3 getS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accesskeyId, accessSecretKey);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }
}
