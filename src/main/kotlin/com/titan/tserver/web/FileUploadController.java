package com.titan.tserver.web;

import com.titan.tserver.model.ResultData;
import com.titan.tserver.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileUploadController {


    /*@GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        return "uploadForm";
    }
    //跳转到上传文件的页面
    @GetMapping("/uploadfile")
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "upload";
    }*/

    @PostMapping("scly/uploadfile/")
    public ResultData uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        String UploadPath =request.getContextPath()+"UploadFiles/images";

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        //String filePath = request.getSession().getServletContext().getRealPath("uploadfile/");
        try {
            FileUtil.uploadFile(file.getBytes(), UploadPath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("上传异常"+e);
            return new ResultData(false, "文件上传", "上传异常"+e);

        }
        return new ResultData(true, "文件上传", "上报成功");
    }


    @PostMapping("scly/uploadfiles/")
    public ResultData uploadFiles(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){

        String UploadPath =request.getContextPath()+"UploadFiles/images";
        System.out.println("文件上传"+UploadPath);

        for (MultipartFile file:files) {
            try {
                String fileName=file.getName();
                FileUtil.uploadFile(file.getBytes(), UploadPath, fileName);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("上传异常"+e);
                return new ResultData(false, "文件上传", "上传异常"+e);
            }
        }
        return new ResultData(true, "文件上传", "上传成功");
    }





}
