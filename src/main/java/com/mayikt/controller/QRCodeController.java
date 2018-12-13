package com.mayikt.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.util.QRCodeUtil;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @RequestMapping(value = "download")
    public void dowloadQRCode(HttpServletRequest request, HttpServletResponse response, String paEncryptId) throws IOException {
    	OutputStream outputStream = null;
    	try {
    		String qrCodeUrl = "www.baidu.com";
        	File logo = new File("C:\\Users\\Administrator\\Desktop\\MyFile\\girl.jpg");
        	String fileName = "test" + ".png";
        	String logoText = "logoText";
        	String format = "png"; // 二维码图片的格式
        	
    		response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            outputStream = response.getOutputStream();
			setResponseHeader(request, response, fileName);
        	//生成二维码
        	QRCodeUtil.createImageWithLogoTextToStream(qrCodeUrl, 3508, 3508, logoText, 458, 458, 50, logo, format, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
