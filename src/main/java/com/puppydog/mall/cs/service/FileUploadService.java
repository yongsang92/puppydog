package com.puppydog.mall.cs.service;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;

@Service
@Slf4j
public class FileUploadService {

    @Autowired
    ResourceLoader resourceLoader;

  

    // 썸네일 사진 만들고 업로드
    public String upload(MultipartFile multipartFile) throws Exception {
   
        String fileName = multipartFile.getOriginalFilename();
        String filePath= resourceLoader.getResource("classpath:static/thumb/" +fileName).getFile().getAbsolutePath(); // 이 경로에서 파일을 찾아서 읽고 이 경로에 썸네일 사진을 만든다 
        String fileType = fileName.substring(fileName.length() - 3, fileName.length());

        return makeThumbnail(filePath, fileName, fileType); // return thumnailPath;
 
    }

    private String makeThumbnail(String filePath, String fileName, String fileType) throws Exception {
        log.warn("making thumbnail Pictures");
        BufferedImage srcImg = ImageIO.read(new File(filePath));

        int dw = 250, dh = 150;
        int ow = srcImg.getWidth();
        int oh = srcImg.getHeight();

        int nw = ow;
        int nh = (ow * dh) / dw;

        if (nh > oh) {
            nw = (oh * dw) / dh;
            nh = oh;
        }

        BufferedImage cropImg = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh);
        BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
        String thumbName = filePath + "_thumb" + "." + fileType;

        File thumbFile = new File(thumbName);
        ImageIO.write(destImg, fileType, thumbFile);

        return thumbFile.getName();

    }

}