package com.mayikt.controller;

import com.mayikt.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MinioController {

    @Autowired
    private MinioUtil minioUtil;

    @GetMapping("/upload")
    public String upload() {
        boolean b = minioUtil.uploadObject("test/test1/2.jpg", "C:/Users/Administrator/Pictures/Saved Pictures/20186517465478125.jpg");
        return "test";
    }

    @GetMapping("/download")
    public String download() {
        minioUtil.downloadObject("test/1.jpg", "C:/Users/Administrator/Pictures/Saved Pictures/1.jpg");
        return "test";
    }

    @GetMapping("/downloadFolder")
    public String downloadFolder(HttpServletResponse response) {
        minioUtil.downloadFolderObject(response, "test");
        return "test";
    }

    @GetMapping("/delete")
    public String delete() {
        minioUtil.deleteObject("test/1.jpg");
        return "test";
    }

}
