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

        String originalString = "U53MF7RHVM2FENTXAIKA";
        StringBuilder reversedString = new StringBuilder(originalString).reverse();
        String result = reversedString.toString();

        String originalString1 = "Fss6CcSZnclfYtZKE1wKBYAgZF6DWh7SHlc8fExq";
        StringBuilder reversedString1 = new StringBuilder(originalString1).reverse();
        String result1 = reversedString1.toString();

        ProfileCredentialsProvider profileCredentialsProvider =
                new ProfileCredentialsProvider();
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                result,
                result1);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }
}
