package com.mayikt.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mayikt.util.ExcelUtil;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	
	@Autowired
	private ExcelUtil excelUtil;

	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("download");
		String fileName = "details.xls";
		excelUtil.download(request, response, fileName);
	}
	
	@RequestMapping("/upload")
	public void upload(@RequestParam(value="file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
		System.out.println("upload");
		if(file.isEmpty()){
            return;
        }
		
		String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        
        // 上传后记录的文件...
        String dir = System.getProperty("java.io.tmpdir");
        // 临时文件路径
        String filePath = dir + File.separator + fileName;
        File dest = new File(filePath);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        
        try {
            file.transferTo(dest); //保存文件
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		excelUtil.upload(request, response, dest);
	}
	
}
