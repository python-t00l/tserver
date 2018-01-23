package com.titan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class TestController {
    @GetMapping("/test")
    public String listUploadedFiles() throws IOException {
        return "uploadForm";
    }
}
