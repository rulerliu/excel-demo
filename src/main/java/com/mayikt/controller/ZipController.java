package com.mayikt.controller;

import com.google.zxing.WriterException;
import com.mayikt.exception.ResourceException;
import com.mayikt.util.QRCodeUtil;
import com.mayikt.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/zip")
@Slf4j
public class ZipController {

	@RequestMapping(value = "download2")
	public void getCompressedFileZip(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=\"template_packages.zip\"");

			Map<String, Integer> nameCountMap = new HashMap<>();

			ByteArrayOutputStream bufferOutput = new ByteArrayOutputStream();
			try (ZipOutputStream outputStream = new ZipOutputStream(bufferOutput)) {
//				for (String fileId : fileIdList) {
					File file = new File("C:\\Users\\Administrator\\Downloads\\idea相关配置.txt");
					ZipEntry zipEntry = new ZipEntry(file.getName());
					outputStream.putNextEntry(zipEntry);
//					  try (ObjectStatVO objectStatVO = MinioUtil.downloadAttachment(zoneServerConfig, file.getFolderName(), file.getName())) {
//                        StreamUtils.copy(objectStatVO.getInputStream(), outputStream);
//                    } catch (Throwable e) {
//                        log.warn("压缩文件时失败：", e);
//                    }
					StreamUtils.copy(new FileInputStream(file), outputStream);
					outputStream.closeEntry();
//				}
			}
			StreamUtils.copy(bufferOutput.toByteArray(), response.getOutputStream());
		} catch (IOException e) {
			throw new ResourceException("生成压缩文件包失败！");
		}
	}

    @RequestMapping(value = "download")
    public void dowloadQRCode(HttpServletRequest request, HttpServletResponse response, String paEncryptId) throws IOException {
		ZipOutputStream zipOutputStream = null;
		OutputStream outputStream = null;
		try {
			int width = 3508; // 二维码图片的宽
			int height = 3508; // 二维码图片的高
			String fileName = "myzip.zip";
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			setResponseHeader(request, response, fileName);
			outputStream = response.getOutputStream();
			zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
			zipOutputStream.setMethod(ZipOutputStream.DEFLATED);// 设置压缩方法
			for (int i = 0; i < 3; i++) {
				try {
//					String qrCodeUrl = "www.baidu.com";
//		        	File logo = new File("C:\\Users\\Administrator\\Desktop\\MyFile\\girl.jpg");
//		        	String logoText = "logoText";
//		    		
//		        	//生成二维码
//		        	BufferedImage bufferedImage = QRCodeUtil.createImageWithLogoTextToStream(qrCodeUrl, width, height, logoText, 458, 458, 50, logo, format, outputStream);
//					ZipUtil.doZip(zipOutputStream, i + ".png", bufferedImage);
					
					String text = "www.baidu.com?keyword=" + i;
					String name = i + ".png";
		        	//生成二维码
		        	BufferedImage bufferedImage = QRCodeUtil.generateQRCodeImage(text + "", width, height);
					ZipUtil.doZip(zipOutputStream, name, bufferedImage);
				} catch (WriterException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(zipOutputStream != null){
				zipOutputStream.close();
			}
			if(outputStream!=null){
				outputStream.close();
			}
		}
    }
    
    private void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
    }
	
}
