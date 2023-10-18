package com.mayikt.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ZipUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * minio 工具类，提供上传、下载方法
 */
@Slf4j
@Component
public class MinioUtil {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.folder}")
    private String folder;

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
                //设置全局访问策略
                setBucketPolicy();
                log.info("minioClient create end");
            } else {
                //设置全局访问策略
                setBucketPolicy();
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
    private void createBucket()
            throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException, RegionConflictException {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    private void setBucketPolicy() throws InvalidBucketNameException {
        try {
            String folderName = folder.substring(0, folder.length() - 1);
//            String json="{\n" +
//                    "    \"Version\": \"2012-10-17\",\n" +
//                    "    \"Statement\": [\n" +
//                    "        {\n" +
//                    "            \"Effect\": \"Allow\",\n" +
//                    "            \"Action\": [\n" +
//                    "                \"s3:GetObject\",\n" +
//                    "                \"s3:ListAllMyBuckets\",\n" +
//                    "                \"s3:ListBucket\",\n" +
//                    "                \"s3:PutObject\",\n" +
//                    "                \"s3:DeleteObject\",\n" +
//                    "                \"s3:GetBucketLocation\"\n" +
//                    "            ],\n" +
//                    "          \"Principal\": {\"AWS\":[\"*\"]},\n" +
//                    "            \"Resource\": [\n" +
//                    "                \"arn:aws:s3:::icloud-test\",\n" +
//                    "                \"arn:aws:s3:::icloud-test/**\"\n" +
//                    "            ]\n" +
//                    "        }\n" +
//                    "    ]\n" +
//                    "}";
            String config = "{\n" +
                    "     \"Statement\": [\n" +
                    "         {\n" +
                    "             \"Action\": [\n" +
                    "                 \"s3:GetBucketLocation\",\n" +
                    "                 \"s3:ListBucket\"\n" +
                    "             ],\n" +
                    "             \"Effect\": \"Allow\",\n" +
                    "             \"Principal\": \"*\",\n" +
                    "             \"Resource\": \"arn:aws:s3:::" + bucketName + "\"\n" +
                    "         },\n" +
                    "         {\n" +
                    "       \"Action\": [\n" +
                    "                \"s3:GetObject\",\n" +
                    "                \"s3:PutObject\",\n" +
                    "                \"s3:DeleteObject\"\n" +
                    "      ],\n" +
                    "             \"Effect\": \"Allow\",\n" +
                    "             \"Principal\": {\"AWS\":[\"*\"]},\n" +
                    "             \"Resource\": \"arn:aws:s3:::" + bucketName + "/" + "**" + "\"\n" +
                    "         }\n" +
                    "     ],\n" +
                    "     \"Version\": \"2012-10-17\"\n" +
                    "}";

            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(config).build());

        } catch (ErrorResponseException | InsufficientDataException
                | InternalException | InvalidKeyException
                | InvalidResponseException | IOException
                | NoSuchAlgorithmException | ServerException
                | XmlParserException e) {
            log.error("minio设置桶:{}策略失败", bucketName, e);
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
     * @param localFilePathName
     * @return
     */
    public boolean uploadObject(String objectName, String localFilePathName) {
        if (isBuckExist(bucketName)) {
            try {
                minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(localFilePathName).build());
                return true;
            } catch (Exception e) {
                log.error("minio upload object file error " + e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 下载对象
     *
     * @param objectName
     * @param localFilePathName
     * @return
     */
    public boolean downloadObject(String objectName, String localFilePathName) {
        if (isBuckExist(bucketName)) {
            try {
                minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(objectName).filename(localFilePathName).build());
                return true;
            } catch (Exception e) {
                log.error("minio download object file error " + e.getMessage());
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * 下载对象
     *
     * @param filePath
     * @return
     */
    public boolean downloadFolderObject(HttpServletResponse response, String filePath) {
        if (isBuckExist(bucketName)) {
            try {
                // 获取文件夹内所有内容
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
                    InputStream inputStream = minioClient.getObject(bucketName, fileUrl);
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

        } else {
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
        if (isBuckExist(bucketName)) {
            try {
                minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
                return true;
            } catch (Exception e) {
                log.error("minio delete object file error " + e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

}