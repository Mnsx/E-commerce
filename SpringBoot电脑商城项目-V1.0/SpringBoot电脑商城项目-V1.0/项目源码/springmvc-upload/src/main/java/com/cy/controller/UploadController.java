package com.cy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    /*
    @RequestMapping("upload.do")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println("UploadController.upload()...");
        File dest = new File("D:/1.png");
        try {
            // 调用MultipartFile参数对象的transferTo()方法即可保存上传的文件
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";
    }
    */

    @RequestMapping("upload.do")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        // 获取上传文件的原始文件名
        String originalFilename = file.getOriginalFilename();
        // 获取上下文的绝对路径
        String realPath = request.getServletContext().getRealPath("upload");
        System.out.println(realPath);
        // 创建File文件对象
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 自定义上传文件名
        String fileName = UUID.randomUUID().toString();
        // 获取上传文件扩展名
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String fullFilename = fileName + suffix;
        // 调用MultipartFile参数对象的transferTo()方法即可保存上传的文件
        file.transferTo(new File(dir, fullFilename));

        return "OK";
    }

}
