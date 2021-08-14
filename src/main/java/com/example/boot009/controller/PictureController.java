package com.example.boot009.controller;



import com.example.boot009.entity.Picture;
import com.example.boot009.repository.PictureRepository;
import com.example.boot009.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@RestController
public class PictureController {
    @GetMapping("/findPicture/{page}/{size}")
    public Page<Picture> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return pictureRepository.findAll(request);
    }
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "H:/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8181/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @Autowired
    PictureRepository pictureRepository;
    @PostMapping("/api/save")
    public String save(@RequestBody Picture picture){
        Picture result = pictureRepository.save(picture);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }




}
