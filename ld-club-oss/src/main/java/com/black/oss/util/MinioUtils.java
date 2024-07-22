package com.black.oss.util;

import com.black.oss.entity.FileInfo;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MinioUtils {

    @Resource
    private MinioClient minioClient;
    /**
     * 创建桶
     * @param bucketName
     * @throws Exception
     */
    public void createBucket(String bucketName) throws  Exception{
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

    }
    /**
     * 删除桶
     * @param bucketName
     * @throws Exception
     */
    public void deleteBucket(String bucketName) throws Exception{
        if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        }
    }
    /**
     * 桶是否存在
     * @param bucketName
     * @return Boolean
     * @throws Exception
     */
    public boolean bucketExists(String bucketName) throws Exception{
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }
    /**
     * 获取所有桶的名字
     * @return
     * @throws Exception
     */
    public List<String> getBucketList() throws Exception{
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }
    /**
     * 列出所有桶的文件
     * @param bucket
     * @return
     * @throws Exception
     */
    public List<FileInfo> getAllFile(String bucket) throws Exception{
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucket).build());
        List<FileInfo> fileInfoList = new LinkedList<>();
        for (Result<Item> result : results) {
            FileInfo fileInfo = new FileInfo();
            Item item = result.get();
            fileInfo.setFileName(item.objectName());
            fileInfo.setDirectoryFlag(item.isDir());
            fileInfo.setEtag(item.etag());
            fileInfoList.add(fileInfo);
        }
        return fileInfoList;
    }
    /**
     * 上传文件
     * @throws Exception
     */
    public void uploadFile(InputStream inputStream, String bucket, String objectName) throws Exception {
        if (!bucketExists(bucket)){
            minioClient.putObject(PutObjectArgs.builder().bucket(bucket).object(objectName)
                    .stream(inputStream, -1, 5242889L).build());
        }
        else {
            throw new Exception("桶不存在!");
        }

    }
    /**
     * 删除文件
     * @param bucket
     * @param objectName
     * @throws Exception
     */
    public void deleteFile(String bucket, String objectName) throws Exception {
        if (!bucketExists(bucket)){
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objectName).build());
        }
        else {
            throw new Exception("桶不存在!");
        }

    }
    /**
     * 下载文件
     * @param bucket
     * @param objectName
     * @return
     * @throws Exception
     */
    public InputStream downloadFile(String bucket, String objectName) throws Exception {
        if (!bucketExists(bucket)) {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
        }
        else {
            throw new Exception("桶不存在!");
        }
    }
    public String getUrl(String bucketName, String objectName) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET).bucket(bucketName).object(objectName).build());
    }


}
