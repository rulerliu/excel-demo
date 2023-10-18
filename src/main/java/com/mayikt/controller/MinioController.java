package com.mayikt.controller;

import com.mayikt.util.MinioUtil;
import com.mayikt.vo.ObjectStatVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioUtil minioUtil;

    @GetMapping("/upload")
    public String upload() {
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        boolean b = minioUtil.uploadObject(dateDir + "1.jpg", "C:/Users/Administrator/Pictures/Saved Pictures/20186517465478125.jpg");
        return "test";
    }

    @GetMapping("/upload2")
    public String upload2(MultipartFile file) {
        String filename = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        //文件路径
        String tempDir = System.getProperty("java.io.tmpdir");
        File target = new File(tempDir + File.separator + filename);

        try {
            file.transferTo(target); //把myfile转移到目标目录下
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
             minioUtil.uploadObject(dateDir + file.getOriginalFilename(), new FileInputStream(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "test";
    }

    @GetMapping("/download")
    public String download() {
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        minioUtil.downloadObject(dateDir + "1.jpg", "C:/Users/Administrator/Pictures/Saved Pictures/1.jpg");
        return "test";
    }

    @GetMapping("/download2")
    public void download2(HttpServletResponse response) {
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String objectName = dateDir + "1.jpg";
        try (ObjectStatVO objectStatVO = minioUtil.downloadObject(response, objectName)) {
            response.setContentType(objectStatVO.getStat().contentType());
            response.setContentLengthLong(objectStatVO.getStat().length());
            String[] split = objectName.split("/");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(split[split.length - 1], "utf-8") + "\"");
            StreamUtils.copy(objectStatVO.getInputStream(), response.getOutputStream());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @GetMapping("/preview")
    public void preview(HttpServletResponse response) {
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        String objectName = dateDir + "1.jpg";
        try (ObjectStatVO objectStatVO = minioUtil.downloadObject(response, objectName)) {
            response.setContentType(objectStatVO.getStat().contentType());
            response.setContentLengthLong(objectStatVO.getStat().length());
            String[] split = objectName.split("/");
            response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(split[split.length - 1], "utf-8") + "\"");
            StreamUtils.copy(objectStatVO.getInputStream(), response.getOutputStream());
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @GetMapping("/downloadFolder")
    public String downloadFolder(HttpServletResponse response) {
        minioUtil.downloadFolderObject(response, "test");
        return "test";
    }

    @GetMapping("/delete")
    public String delete() {
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        minioUtil.deleteObject(dateDir + "1.jpg");
        return "test";
    }

}
