package kz.sdu.finance_tracker.service;


import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @PostConstruct
    public void initBucket() {
        try {
            BucketExistsArgs build = BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build();
            boolean isExist = minioClient.bucketExists(build);
            if (!isExist) {
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build();
                minioClient.makeBucket(makeBucketArgs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MinIO bucket: " + bucketName, e);
        }
    }

    public String saveToMinio(String image) {
        try {
            String fileName = UUID.randomUUID().toString();
            if (image.startsWith("data:image")) {
                image = image.substring(image.indexOf(",") + 1);
            }
            byte[] imageBytes = Base64.getDecoder().decode(image);
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(imageBytes), imageBytes.length, -1)
                    .contentType("image/jpeg")
                    .build()
            );
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO", e);
        }
    }

    public InputStream getImage(String objectName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении изображения из MinIO: " + objectName, e);
        }
    }
}
