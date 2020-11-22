package com.test.updrepo.servicios;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BucketServicios {
    @Value("${amazon.aws.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 s3;

    public void guardaReporte(String fileName, InputStream fileContent) {
        s3.putObject(new PutObjectRequest(bucket, fileName, fileContent, new ObjectMetadata()));
    }
}
