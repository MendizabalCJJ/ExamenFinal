package com.test.leerrepodyna;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

@Configuration
@EnableDynamoDBRepositories
public class AmazonAWSConfig {
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3Client.builder()
                             .withRegion(amazonAWSRegion)
                             .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                             .build();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new EndpointConfiguration(dynamoDbEndpoint, amazonAWSRegion))
        .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials())).build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
