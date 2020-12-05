package com.atlaspharmacy.atlaspharmacy.pswregistration.controller;

import com.atlaspharmacy.atlaspharmacy.aws.AmazonClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HttpFileController {
    
    private AmazonClient _amazonClient;
    
    @Autowired
    public HttpFileController(AmazonClient amazonClient)
    {
        this._amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this._amazonClient.uploadFile(file);
    }
}
