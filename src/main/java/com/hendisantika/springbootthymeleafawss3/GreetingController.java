package com.hendisantika.springbootthymeleafawss3;

import com.hendisantika.springbootthymeleafawss3.service.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-thymeleaf-aws-s3
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/01/21
 * Time: 05.56
 */
@Log4j2
@Controller
public class GreetingController {
    @Value("${application.bucket.name}")
    String bucketName;

    @Autowired
    private StorageService service;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestParam(name = "name", required = true) String name) {
        ModelAndView mv = new ModelAndView("greeting");
        mv.addObject("name", name + "lol");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView renderUploadPage() {
        return new ModelAndView("uploadFiles");
    }

    @PostMapping(value = "/upload")
    public ModelAndView uploadS3(@RequestParam("photo") MultipartFile image, @RequestParam(name = "desc") String desc) {
        ModelAndView returnPage = new ModelAndView();
        log.info("description      " + desc);
        log.info(image.getOriginalFilename());

        String imgName = service.uploadFile(image);
        String imgSrc = "http://" + bucketName + ".s3-ap-southeast-1.amazonaws.com/" + imgName;
        returnPage.setViewName("showImage");
        returnPage.addObject("name", desc);
        returnPage.addObject("imgSrc", imgSrc);
        return returnPage;

    }

}
