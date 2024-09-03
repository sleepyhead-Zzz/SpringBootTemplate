package com.springboottemplate.admin.infrastructure.storage;

import cn.hutool.core.lang.UUID;
import io.minio.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class MinioUtil {

    @Value("${minio.bucketName}")
    private String bucketName;

    @Resource
    private MinioClient minioClient;

    /**
     * description: 判断bucket是否存在，不存在则创建
     */
    public void existBucket(String name) {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            // 如果Bucket不存在，则创建Bucket
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                log.info("成功创建 Bucket [{}]", name);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Upload Single File
     */
    public String upload(MultipartFile file) throws Exception {
        // 获取文件后缀名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 为了避免文件名重复，使用UUID重命名文件，将横杠去掉
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
        // 上传
        this.putObject(file.getInputStream(), fileName, file.getContentType());
        // 返回文件名
        return fileName;
    }

    /**
     * 上传文件
     *
     * @param is 输入流
     * @param object 对象（文件）名
     * @param contentType 文件类型
     */
    private void putObject(InputStream is, String object, String contentType) throws Exception {
        long start = System.currentTimeMillis();
        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucketName)
            .object(object)
            .contentType(contentType)
            .stream(is, -1, 1024 * 1024 * 10) // 不得小于 5 Mib
            .build());
        log.info("成功上传文件至云端 [{}]，耗时 [{} ms]", object, System.currentTimeMillis() - start);
    }

    /**
     * 获取文件流
     *
     * @param object 对象（文件）名
     * @return 文件流
     */
    private GetObjectResponse getObject(String object) throws Exception {
        long start = System.currentTimeMillis();
        GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder()
            .bucket(bucketName)
            .object(object)
            .build());
        log.info("成功获取 Object [{}]，耗时 [{} ms]", object, System.currentTimeMillis() - start);
        return response;
    }

    /**
     * 删除对象（文件）
     *
     * @param object 对象（文件名）
     */
    public void removeObject(String object) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
            .bucket(bucketName)
            .object(object)
            .build());
        log.info("成功删除 Object [{}]", object);
    }
}
