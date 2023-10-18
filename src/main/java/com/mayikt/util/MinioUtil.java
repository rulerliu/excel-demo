package com.mayikt.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ZipUtil;
import com.mayikt.vo.ObjectStatVO;
import io.minio.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * minio 工具类，提供上传、下载方法
 */
@Slf4j
@Component
public class MinioUtil {

//    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @PostConstruct
    private void handlerInit() {
        createMinioClient();
        log.info("MinIo初始化成功");
    }

    /**
     * 创建minioClient
     */
    private void createMinioClient() {
        try {
            if (null == minioClient) {
                log.info("minioClient create start");
                minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey)
                        .build();
                createBucket();
                log.info("minioClient create end");
            }
        } catch (Exception e) {
            log.error("连接MinIO服务器异常：{}", e);
        }
    }

    /**
     * 初始化Bucket
     *
     * @throws Exception 异常
     */
    private void createBucket() {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config().build());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }


    /**
     * 判断bulk桶是否存在
     *
     * @param bulkName
     * @return
     */
    private boolean isBuckExist(String bulkName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bulkName).build());
        } catch (Exception e) {
            log.error("bulkname is not exist,error " + e.getMessage());
            return false;
        }
    }

    /**
     * 上传对象
     *
     * @param objectName
     * @param inputStream
     * @return
     */
    public boolean uploadObject(String objectName, InputStream inputStream) {
        if (!isBuckExist(bucketName)) {
           createBucket();
        }

        try {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, inputStream.available(), -1).build());
            return true;
        } catch (Exception e) {
            log.error("minio upload object file error " + e.getMessage());
            return false;
        }
    }

    /**
     * 从本地目录上传对象
     *
     * @param objectName
     * @param localFilePathName
     * @return
     */
    public boolean uploadObject(String objectName, String localFilePathName) {
        if (!isBuckExist(bucketName)) {
            createBucket();
        }

        try {
            minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(localFilePathName).build());
            return true;
        } catch (Exception e) {
            log.error("minio upload object file error " + e.getMessage());
            return false;
        }
    }

    /**
     * 下载对象
     *
     * @param objectName
     * @return
     */
    public ObjectStatVO downloadObject(HttpServletResponse response, String objectName) {
        if (!isBuckExist(bucketName)) {
            createBucket();
        }

        try {
            ObjectStat stat = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return ObjectStatVO.builder().stat(stat).inputStream(inputStream).build();
        } catch (Exception e) {
            log.error("minio download object file error " + e.getMessage());
        }
        return null;
    }

    /**
     * 下载对象到某个目录
     *
     * @param objectName
     * @param localFilePathName
     * @return
     */
    public boolean downloadObject(String objectName, String localFilePathName) {
        if (!isBuckExist(bucketName)) {
            createBucket();
        }

        try {
            minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(objectName).filename(localFilePathName).build());
            return true;
        } catch (Exception e) {
            log.error("minio download object file error " + e.getMessage());
            return false;
        }
    }

    /**
     * 下载文件夹压缩文件对象
     *
     * @param filePath
     * @return
     */
    public boolean downloadFolderObject(HttpServletResponse response, String filePath) {
        if (!isBuckExist(bucketName)) {
            createBucket();
        }

        try {
            // 获取文件夹内所有内容
//            Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(filePath).build());
            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName, filePath);
            List<String> fileUrlList = new ArrayList<>();
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                fileUrlList.add(item.objectName());
            }
            //被压缩文件InputStream
            InputStream[] srcFiles = new InputStream[fileUrlList.size()];
            //被压缩文件名称
            String[] srcFileNames = new String[fileUrlList.size()];
            for (int i = 0; i < fileUrlList.size(); i++) {
                String fileUrl = fileUrlList.get(i);
                InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileUrl).build());
                if (inputStream == null) {
                    continue;
                }
                srcFiles[i] = inputStream;
                String[] splitFileUrl = fileUrl.split("/");
                srcFileNames[i] = CollectionUtil.join(Arrays.asList(Arrays.copyOfRange(splitFileUrl, 1, splitFileUrl.length)), "/");
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filePath + ".zip", "UTF-8"));
            //多个文件压缩成压缩包返回
            ZipUtil.zip(response.getOutputStream(), srcFileNames, srcFiles);
            return true;
        } catch (Exception e) {
            log.error("minio download object file error " + e.getMessage());
            return false;
        }

    }


    /**
     * 删除对象
     *
     * @param objectName
     * @return
     */
    public boolean deleteObject(String objectName) {
        if (!isBuckExist(bucketName)) {
            createBucket();
        }

        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        } catch (Exception e) {
            log.error("minio delete object file error " + e.getMessage());
            return false;
        }
    }

}