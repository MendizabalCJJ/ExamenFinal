package com.test.leerrepodyna.servicios;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    @Value("${amazon.aws.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 s3;

    public InputStream getRepoXLS(String nombreRepo) {
        S3Object s3Obj = s3.getObject(new GetObjectRequest(bucket, nombreRepo));
        return s3Obj != null? s3Obj.getObjectContent().getDelegateStream() : null;
    }
}
