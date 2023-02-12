package com.blasarcesh.s3test.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfilesConfigFile;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

@Configuration
public class S3Config {
    @Value("${aws.s3.region}")
    private String region;


    @Bean
    public AmazonS3 getS3Client() {
        ProfileCredentialsProvider profileCredentialsProvider =
                new ProfileCredentialsProvider();
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                profileCredentialsProvider.getCredentials().getAWSAccessKeyId(),
                profileCredentialsProvider.getCredentials().getAWSSecretKey());
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }
}
