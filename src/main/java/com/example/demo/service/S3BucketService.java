package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3BucketService {

    private static final Logger logger= LoggerFactory.getLogger("S3BucketService.class");
    private final S3Client client;
    @Value("${aws.bucketname}")
    private String bucket_name;
    S3BucketService(S3Client client)
    {
        this.client=client;
    }

    public String uploadService(MultipartFile file)
    {
        try{
            logger.info("Upload Service executed");
            String key=file.getOriginalFilename();
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket_name)
                    .key(key)
                    .contentType(file.getContentType())
                  // .acl("public-read")
                    .build();
            client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            return "https://"+bucket_name+".s3.amazonaws.com/"+key;

        }
        catch (Exception e)
        {
            logger.error("Upload Failed");
            throw new RuntimeException(e);
        }
    }
}
