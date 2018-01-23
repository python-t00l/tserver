package com.titan;

import com.titan.tserver.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUpload {
    private final StorageService storageService;

    @Autowired
    public FileUpload(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/")
    public String  uploadFiles(@RequestParam("file") MultipartFile[] files) {

        for (MultipartFile file:files){
            storageService.store(file);
        }
        return "redirect:/";
    }


}
